package com.musicsite.controller;

import com.musicsite.repository.AlbumRepository;
import com.musicsite.repository.CategoryRepository;
import com.musicsite.repository.PerformerRepository;
import com.musicsite.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/performer")
public class PerformerController {

    private PerformerRepository performerRepository;
    private TrackRepository trackRepository;
    private AlbumRepository albumRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public PerformerController(PerformerRepository performerRepository, TrackRepository trackRepository, AlbumRepository albumRepository, CategoryRepository categoryRepository) {
        this.performerRepository = performerRepository;
        this.trackRepository = trackRepository;
        this.albumRepository = albumRepository;
        this.categoryRepository = categoryRepository;
    }


}
