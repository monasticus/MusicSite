package com.musicsite.controller;

import com.musicsite.entity.Performer;
import com.musicsite.entity.Track;
import com.musicsite.service.AlbumService;
import com.musicsite.service.PerformerService;
import com.musicsite.service.RankingService;
import com.musicsite.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ranking")
public class RankingController {

    PerformerService performerService;
    AlbumService albumService;
    TrackService trackService;

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
                model.addAttribute("enta", performerService.getPerformers());
                break;
            case "albums":
                model.addAttribute("enta", albumService.getAlbums());
                break;
            case "tracks":
                model.addAttribute("enta", trackService.getTracks());
                break;
            default:
                return "main/blank";
        }


        return "ranking/".concat(enta);
    }
}
