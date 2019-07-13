package com.musicsite.controller;

import com.musicsite.entity.Album;
import com.musicsite.entity.Performer;
import com.musicsite.repository.AlbumRepository;
import com.musicsite.repository.PerformerRepository;
import com.musicsite.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Book;
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


    // ============================== PERFORMER

    @GetMapping("/performer")
    public String showPerformerForm(Model model) {

        model.addAttribute("performer", new Performer());

        return "add/performer";
    }

    @PostMapping("/performer")
    public String savePerformer(@Valid Performer performer, BindingResult result, Model model) {
        if (result.hasErrors())
            return "add/performer";

        List<Performer> performers = performerRepository.getPerformersByPseudonym(performer.getPseudonym());
        Boolean duplicate = performers.stream().anyMatch(p -> ifPerformerExists(performer, p));

        if (duplicate) {
            model.addAttribute("duplicate", duplicate);
            return "add/performer";
        }

        performerRepository.save(performer);
        model.addAttribute("success", true);

        return "add/performer";
    }

    private boolean ifPerformerExists(Performer performer, Performer p) {
        return p.getFirstName().equals(performer.getFirstName()) && p.getLastName().equals(performer.getLastName());
    }

    // ============================== ALBUM

    @GetMapping("/album")
    public String showAlbumForm(Model model) {

        model.addAttribute("album", new Album());

        return "add/album";
    }

    @PostMapping("/album")
    public String saveAlbum(@Valid Album album, BindingResult result, Model model) {
        if (result.hasErrors())
            return "add/album";

        List<Album> albums = albumRepository.getAlbumsByName(album.getName());
        Boolean duplicate = albums.stream().anyMatch(a -> ifAlbumExists(album, a));

        if (duplicate) {
            model.addAttribute("duplicate", duplicate);
            return "add/album";
        }

        albumRepository.save(album);
        model.addAttribute("success", true);

        return "redirect:list";
    }

    private boolean ifAlbumExists(Album album, Album a) {
        Boolean duplicatePerformer = a.getPerformers()
                                            .stream()
                                            .anyMatch(p1 -> album.getPerformers()
                                                                        .stream()
                                                                        .anyMatch(p2 -> ifPerformerExists(p1, p2)));

        return a.getYearOfPublication().equals(album.getYearOfPublication()) && duplicatePerformer;
    }

    // ============================== TRACK

}
