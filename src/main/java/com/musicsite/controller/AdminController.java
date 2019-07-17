package com.musicsite.controller;

import com.musicsite.entity.Category;
import com.musicsite.repository.CategoryRepository;
import com.musicsite.service.AlbumService;
import com.musicsite.service.PerformerService;
import com.musicsite.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("adm/")
public class AdminController {

    private PerformerService performerService;
    private TrackService trackService;
    private AlbumService albumService;
    private CategoryRepository categoryRepository;

    @Autowired
    public AdminController(PerformerService performerService, TrackService trackService, AlbumService albumService, CategoryRepository categoryRepository) {
        this.performerService = performerService;
        this.trackService = trackService;
        this.albumService = albumService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/add/category")
    public String showCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category";
    }

    @PostMapping("/add/category")
    public String saveCategory(@Valid Category category, BindingResult result, Model model) {

        if (result.hasErrors())
            return "admin/category";

        boolean duplicate = categoryRepository.findAll().stream().anyMatch(c -> c.getName().equals(category.getName()));
        if (duplicate) {
            model.addAttribute("duplicate", duplicate);
            return "admin/category";
        }

        categoryRepository.save(category);
        model.addAttribute("category", new Category());
        model.addAttribute("success", true);

        return "admin/category";
    }

    @GetMapping("/propositions")
    public String displayPropositions(Model model) {
        model.addAttribute("performers", performerService.getOnlyPerformerPropositions());
        model.addAttribute("albums", albumService.getOnlyAlbumPropositions());
        model.addAttribute("tracks", trackService.getOnlyTrackPropositions());

        return "admin/propositions";

    }

    @RequestMapping("/propositions/{type}/discard")
    public String discardProposition(@PathVariable String type, @RequestParam Long id) {
        switch (type) {
            case "performer":
                performerService.removePerformer(id);
                break;
            case "album":
                albumService.removeAlbum(id);
                break;
            case "track":
                trackService.removeTrack(id);
                break;
        }

        return "redirect:/adm/propositions";
    }

    @RequestMapping("/propositions/{type}/confirm")
    public String confirmProposition(@PathVariable String type, @RequestParam Long id) {
        switch (type) {
            case "performer":
                performerService.confirmPerformer(id);
                break;
            case "album":
                albumService.confirmAlbum(id);
                break;
            case "track":
                trackService.confirmTrack(id);
                break;
        }

        return "redirect:/adm/propositions";
    }
}
