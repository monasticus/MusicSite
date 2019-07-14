package com.musicsite.controller;

import com.musicsite.entity.Category;
import com.musicsite.repository.AlbumRepository;
import com.musicsite.repository.CategoryRepository;
import com.musicsite.repository.PerformerRepository;
import com.musicsite.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("adm/")
public class AdminController {

    private PerformerRepository performerRepository;
    private TrackRepository trackRepository;
    private AlbumRepository albumRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public AdminController(PerformerRepository performerRepository, TrackRepository trackRepository, AlbumRepository albumRepository, CategoryRepository categoryRepository) {
        this.performerRepository = performerRepository;
        this.trackRepository = trackRepository;
        this.albumRepository = albumRepository;
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
}
