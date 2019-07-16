package com.musicsite.controller;

import com.musicsite.service.AlbumService;
import com.musicsite.service.PerformerService;
import com.musicsite.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ranking")
public class RankingController {

    private PerformerService performerService;
    private AlbumService albumService;
    private TrackService trackService;

    @Autowired
    public RankingController(PerformerService performerService, AlbumService albumService, TrackService trackService) {
        this.performerService = performerService;
        this.albumService = albumService;
        this.trackService = trackService;
    }

    @GetMapping("/{enta}")
    public String displayPerformersRanking(@PathVariable String enta, Model model) {

        switch (enta) {
            case "performers":
                model.addAttribute("performers", performerService.getPerformerPropositions());
                return "ranking/performers";
            case "albums":
                model.addAttribute("albums", albumService.getAlbumPropositions());
                return "ranking/albums";
            case "tracks":
                model.addAttribute("tracks", trackService.getTrackPropositions());
                return "ranking/tracks";
            default:
                return "main/blank";
        }

    }
}
