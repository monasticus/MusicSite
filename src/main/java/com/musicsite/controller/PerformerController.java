package com.musicsite.controller;

import com.musicsite.entity.Album;
import com.musicsite.entity.Performer;
import com.musicsite.entity.Track;
import com.musicsite.repository.AlbumRepository;
import com.musicsite.repository.CategoryRepository;
import com.musicsite.repository.PerformerRepository;
import com.musicsite.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/performer")
public class PerformerController {

    private PerformerRepository performerRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;

    @Autowired
    public PerformerController(PerformerRepository performerRepository, AlbumRepository albumRepository, TrackRepository trackRepository) {
        this.performerRepository = performerRepository;
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
    }

    @ModelAttribute("performers")
    public List<Performer> getPerformers() {
        return performerRepository.findAll();
    }

    @GetMapping("/{id}")
    public String showForm(@PathVariable String id, Model model) {
        Performer performer = performerRepository.findOne(Long.parseLong(id));
        if (performer == null)
            return "main/blank";

        performer.setAlbums(albumRepository.getAlbumsByPerformerOrderByYearOfPublicationDesc(performer));
        performer.setTracks(trackRepository.getTracksByPerformerOrderByYearOfPublicationDesc(performer));
        model.addAttribute("performer", performer);

        return "main/performer";
    }

}
