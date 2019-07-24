package com.musicsite.user;

import com.musicsite.album.Album;
import com.musicsite.category.Category;
import com.musicsite.entity.*;
import com.musicsite.performer.Performer;
import com.musicsite.album.AlbumService;
import com.musicsite.category.CategoryService;
import com.musicsite.performer.PerformerService;
import com.musicsite.track.TrackService;
import com.musicsite.track.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/add")
public class AddController {

    private PerformerService performerService;
    private TrackService trackService;
    private AlbumService albumService;
    private CategoryService categoryService;

    @Autowired
    public AddController(PerformerService performerService, TrackService trackService, AlbumService albumService, CategoryService categoryService) {
        this.performerService = performerService;
        this.trackService = trackService;
        this.albumService = albumService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryService.getCategoriesSaute();
    }

    @GetMapping("/{className:performer|album|track}")
    public String showForm(@PathVariable String className, Model model, @RequestParam(required = false) Long id) {

        switch (className) {
            case "performer":
                if (id != null)
                    model.addAttribute(className, performerService.getPerformerById(id));
                else
                    model.addAttribute(className, new Performer());
                break;
            case "album":
                if (id != null) {
                    Album album = albumService.getAlbum(id);
                    model.addAttribute("loadPerformerName", album.getPerformer().getPseudonym());
                    model.addAttribute(className, album);
                } else
                    model.addAttribute(className, new Album());
                break;
            case "track":
                if (id != null) {
                    Track track = trackService.getTrack(id);
                    model.addAttribute("loadPerformerName", track.getPerformer().getPseudonym());
                    if (track.getAlbum() != null)
                        model.addAttribute("loadAlbumName", track.getAlbum().getName());
                    model.addAttribute(className, track);
                } else
                    model.addAttribute(className, new Track());
                break;
        }

        return "add/" + className;
    }


    // ============================== PERFORMER

    @PostMapping("/performer")
    public String savePerformer(@Valid Performer performer, BindingResult result, Model model) {
        if (result.hasErrors())
            return "add/performer";

        List<Performer> performers = performerService.getPerformersByPseudonymSaute(performer.getPseudonym());

        boolean duplicate = false;

        if (performers.size() != 0)
            duplicate = performers.stream().anyMatch(p -> ifPerformerExists(performer, p));

        if (duplicate) {
            model.addAttribute("duplicate", duplicate);
            return "add/performer";
        }

        performerService.save(performer);
        model.addAttribute("performer", new Performer());
        model.addAttribute("success", true);

        return "add/performer";
    }

    private boolean ifPerformerExists(Performer performer, Performer p) {
        return p.getFirstName().equals(performer.getFirstName()) && p.getLastName().equals(performer.getLastName());
    }

    // ============================== ALBUM

    @PostMapping("/album")
    public String saveAlbum(@Valid Album album, BindingResult result, Model model, @RequestParam String performerName) {
        performerName = performerName.trim().toLowerCase();

        // --- Validate inputs

        Performer existingPerformer = checkPerformer(model, performerName);

        if (result.hasErrors())
            return "add/album";

        if (existingPerformer == null)
            return "add/album";

        album.setPerformer(existingPerformer);

        // --- Validate album
        List<Album> albums = albumService.getAlbumsByNameSaute(album.getName());

        boolean duplicate = false;

        if (albums.size() != 0)
            duplicate = albums.stream().anyMatch(a -> ifOpusExists(album, a));

        if (duplicate) {
            model.addAttribute("duplicate", duplicate);
            return "add/album";
        }



        albumService.save(album);

        model.addAttribute("album", new Album());
        model.addAttribute("success", true);

        return "add/album";
    }

    // ============================== TRACK

    @PostMapping("/track")
    public String saveTrack(@Valid Track track, BindingResult result, Model model, @RequestParam String albumName, @RequestParam String performerName) {

        performerName = performerName.trim().toLowerCase();
        albumName = albumName.trim().toLowerCase();

        // --- Validate inputs

        Performer performer = checkPerformer(model, performerName);
        Album album = null;

        if (!"".equals(albumName))
            album = checkAlbum(model, albumName, performer);


        if (result.hasErrors())
            return "add/track";

        if (performer == null || (!"".equals(albumName) && album == null))
            return "add/track";


        track.setPerformer(performer);
        track.setAlbum(album);

        // --- Validate track
        List<Track> tracks = trackService.getTracksByNameSaute(track.getName());

        boolean duplicate = false;

        if (tracks.size() != 0)
            duplicate = tracks.stream().anyMatch(t -> ifOpusExists(track, t));

        if (duplicate) {
            model.addAttribute("duplicate", duplicate);
            return "add/track";
        }

        if (!"".equals(albumName) && !track.getYearOfPublication().equals(track.getAlbum().getYearOfPublication())){
            result.addError (new FieldError("track","yearOfPublication","The year of publication of the following album is different from the one given to the song. Album's year of publication is: "+ track.getAlbum().getYearOfPublication()));
            return "add/track";
        }



        trackService.save(track);

        model.addAttribute("track", new Track());
        model.addAttribute("success", true);

        return "add/track";
    }

    private boolean ifOpusExists(Opus opus, Opus o) {

        Boolean duplicateYears = o.getYearOfPublication().equals(opus.getYearOfPublication());

        Boolean duplicatePerformers = o.getPerformer().equals(opus.getPerformer());

        return duplicateYears && duplicatePerformers;
    }

    private Performer checkPerformer(Model model, String performerName) {

        if ("".equals(performerName)) {
            model.addAttribute("emptyPerformerName", true);
            return null;
        }

        Performer performer = performerService.getPerformerByPseudonymSaute(performerName);

        if(performer == null)
            model.addAttribute("performerDoesNotExists", true);


        return performer;
    }

    private Album checkAlbum(Model model, String albumName, Performer performer) {
        Album album = null;
        if (albumService.getAlbumsByNameSaute(albumName).stream().anyMatch(a -> a.getPerformer().getId().equals(performer.getId())))
            album = albumService.getAlbumsByNameSaute(albumName).stream().filter(a -> a.getPerformer().getId().equals(performer.getId())).findFirst().get();

        if(album == null)
            model.addAttribute("albumDoesNotExists", true);

        return album;
    }

}
