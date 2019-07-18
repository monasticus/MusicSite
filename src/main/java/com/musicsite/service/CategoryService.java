package com.musicsite.service;

import com.musicsite.entity.Category;
import com.musicsite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    private PerformerRepository performerRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(PerformerRepository performerRepository,
                           AlbumRepository albumRepository,
                           TrackRepository trackRepository,
                           UserRepository userRepository,
                           RatingRepository ratingRepository,
                           CategoryRepository categoryRepository) {

        this.performerRepository = performerRepository;
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getActiveCategories() {
        List<Category> activeCategories =
                getCategories()
                        .stream()
                        .filter(c -> c.getAlbums().size() != 0 || c.getTracks().size() != 0)
                        .collect(Collectors.toList());
        if (activeCategories.size() == 0)
            return null;
        else
            return activeCategories;
    }

    public void save (Category category) {
        categoryRepository.save(category);
    }

}
