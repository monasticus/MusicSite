package com.musicsite.controller;

import com.musicsite.service.AlbumService;
import com.musicsite.service.PerformerService;
import com.musicsite.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearcherController {

    private PerformerService performerService;
    private AlbumService albumService;
    private TrackService trackService;

    @Autowired
    public SearcherController(PerformerService performerService,
                               AlbumService albumService,
                               TrackService trackService) {
        this.performerService = performerService;
        this.albumService = albumService;
        this.trackService = trackService;
    }

    @RequestMapping("/search")
    public String search(Model model,  @RequestParam(required = false) String q) {
        if(q == null || "".equals(q.trim()))
            return "main/home";
        model.addAttribute("performers", performerService.getPerformersByQuery(q));
        model.addAttribute("albums", albumService.getAlbumsByQuery(q));
        model.addAttribute("tracks", trackService.getTracksByQuery(q));

        return "main/search";
    }
}
