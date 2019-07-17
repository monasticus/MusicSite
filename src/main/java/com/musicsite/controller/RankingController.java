package com.musicsite.controller;

import com.musicsite.entity.CategorySelector;
import com.musicsite.service.AlbumService;
import com.musicsite.service.CategoryService;
import com.musicsite.service.PerformerService;
import com.musicsite.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ranking")
public class RankingController {

    private PerformerService performerService;
    private AlbumService albumService;
    private TrackService trackService;
    private CategoryService categoryService;

    @Autowired
    public RankingController(PerformerService performerService, AlbumService albumService, TrackService trackService, CategoryService categoryService) {
        this.performerService = performerService;
        this.albumService = albumService;
        this.trackService = trackService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{enta}")
    public String displayPerformersRanking(@PathVariable String enta, Model model) {

        switch (enta) {
            case "performers":
                model.addAttribute("performers", performerService.getPerformersWithCategories());
                return "ranking/performers";
            case "albums":
                model.addAttribute("albums", albumService.getAlbumPropositionsAverageOrdered());
                return "ranking/albums";
            case "tracks":
                model.addAttribute("tracks", trackService.getOnlyTracks());
                return "ranking/tracks";
            case "categories" :
                model.addAttribute("categories", categoryService.getActiveCategories());
                model.addAttribute("categorySelector", new CategorySelector());
                return "ranking/categories";
            default:
                return "main/blank";
        }

    }

    @PostMapping("/categories")
    public String showData(@ModelAttribute CategorySelector categorySelector, Model model) {
        model.addAttribute("albums", albumService.getAlbumsByCategories(categorySelector.getCategoryList()));
        model.addAttribute("tracks", trackService.getTracksByCategories(categorySelector.getCategoryList()));
        model.addAttribute("performers", performerService.getPerformersByCategories(categorySelector.getCategoryList()));
        model.addAttribute("categories", categoryService.getActiveCategories());
        return "ranking/categories";
    }
}