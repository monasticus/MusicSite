package com.musicsite.controller;

import com.musicsite.entity.Album;
import com.musicsite.entity.Performer;
import com.musicsite.entity.Track;
import com.musicsite.entity.Opus;
import com.musicsite.repository.AlbumRepository;
import com.musicsite.repository.PerformerRepository;
import com.musicsite.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/add")
public class AddController {

    PerformerRepository performerRepository;
    TrackRepository trackRepository;
    AlbumRepository albumRepository;

    @Autowired
    public AddController(PerformerRepository performerRepository, TrackRepository trackRepository, AlbumRepository albumRepository) {
        this.performerRepository = performerRepository;
        this.trackRepository = trackRepository;
        this.albumRepository = albumRepository;
    }

    @GetMapping("/{className:performer|album|track}")
    public String showForm(@PathVariable String className, Model model) {

        switch (className) {
            case "performer":
                model.addAttribute(className, new Performer());
                break;
            case "album":
                model.addAttribute(className, new Album());
                break;
            case "track":
                model.addAttribute(className, new Track());
                break;
        }

        return "add/"+className;
    }


    // ============================== PERFORMER

    @PostMapping("/performer")
    public String savePerformer(@Valid Performer performer, BindingResult result, Model model) {
        if (result.hasErrors())
            return "add/performer";

        List<Performer> performers = performerRepository.getPerformersByPseudonym(performer.getPseudonym());

        boolean duplicate = false;

        if (performers.size() != 0)
            duplicate = performers.stream().anyMatch(p -> ifPerformerExists(performer, p));

        if (duplicate) {
            model.addAttribute("duplicate", duplicate);
            return "add/performer";
        }

        performerRepository.save(performer);
        model.addAttribute("performer", new Performer());
        model.addAttribute("success", true);

        return "add/performer";
    }

    private boolean ifPerformerExists(Performer performer, Performer p) {
        return p.getFirstName().equals(performer.getFirstName()) && p.getLastName().equals(performer.getLastName());
    }

    // ============================== ALBUM

    @PostMapping("/album")
    public String saveAlbum(@Valid Album album, BindingResult result, Model model) {
        if (result.hasErrors())
            return "add/album";

        List<Album> albums = albumRepository.getAlbumsByName(album.getName());

        boolean duplicate = false;

        if (albums.size() != 0)
            duplicate = albums.stream().anyMatch(a -> ifOpusExists(album, a));

        if (duplicate) {
            model.addAttribute("duplicate", duplicate);
            return "add/album";
        }


        albumRepository.save(album);

        model.addAttribute("album", new Album());
        model.addAttribute("success", true);

        return "add/album";
    }

    // ============================== TRACK

    @PostMapping("/track")
    public String saveTrack(@Valid Track track, BindingResult result, Model model) {
        if (result.hasErrors())
            return "add/track";

        List<Track> tracks = trackRepository.getTracksByName(track.getName());

        boolean duplicate = false;

        if (tracks.size() != 0)
            duplicate = tracks.stream().anyMatch(t -> ifOpusExists(track, t));

        if (duplicate) {
            model.addAttribute("duplicate", duplicate);
            return "add/track";
        }

        trackRepository.save(track);
        model.addAttribute("track", new Track());
        model.addAttribute("success", true);

        return "add/track";
    }

    private boolean ifOpusExists(Opus opus, Opus o) {

        Boolean duplicateYears = o.getYearOfPublication().equals(opus.getYearOfPublication());

        Boolean duplicatePerformers = o.getPerformer().equals(opus.getPerformer());

        return  duplicateYears && duplicatePerformers;
    }

}
