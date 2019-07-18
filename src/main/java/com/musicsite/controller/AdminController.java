package com.musicsite.controller;

import com.musicsite.entity.Category;
import com.musicsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/adm")
public class AdminController {

    private PerformerService performerService;
    private TrackService trackService;
    private AlbumService albumService;
    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public AdminController(PerformerService performerService,
                           TrackService trackService,
                           AlbumService albumService,
                           CategoryService categoryService,
                           UserService userService) {
        this.performerService = performerService;
        this.trackService = trackService;
        this.albumService = albumService;
        this.categoryService = categoryService;
        this.userService = userService;
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

        boolean duplicate = categoryService.getCategories().stream().anyMatch(c -> c.getName().equals(category.getName()));
        if (duplicate) {
            model.addAttribute("duplicate", duplicate);
            return "admin/category";
        }

        categoryService.save(category);
        model.addAttribute("category", new Category());
        model.addAttribute("success", true);

        return "admin/category";
    }

    @GetMapping("/propositions")
    public String displayPropositions(Model model) {
        model.addAttribute("performers", performerService.getOnlyPerformerPropositions());
        model.addAttribute("albums", albumService.getAlbumsByPropositions(true));
        model.addAttribute("tracks", trackService.getTracksByPropositions(true));

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

    @RequestMapping("/{itemType}/remove/{id}")
    public String removeItem(@PathVariable String itemType, @PathVariable Long id) {
        switch (itemType) {
            case "performer":
                performerService.removePerformer(id);
                break;
            case "album":
                albumService.removeAlbum(id);
                break;
            case "track":
                trackService.removeTrack(id);
                break;
            case "user":
                userService.removeUser(id);
        }

        return "redirect:/ranking/".concat(itemType).concat("s");
    }

    @RequestMapping("users")
    public String displayUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin/users";
    }

}
