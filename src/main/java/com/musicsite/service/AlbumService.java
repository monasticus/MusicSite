package com.musicsite.service;

import com.musicsite.entity.Album;
import com.musicsite.entity.Category;
import com.musicsite.entity.Performer;
import com.musicsite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumService {

    private PerformerRepository performerRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public AlbumService(PerformerRepository performerRepository,
                        AlbumRepository albumRepository,
                        TrackRepository trackRepository,
                        UserRepository userRepository,
                        RatingRepository ratingRepository) {

        this.performerRepository = performerRepository;
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<Album> getAlbumsWithPropositions() {
        return albumRepository.findAll();
    }

    public List<Album> getOnlyAlbumPropositions() {
        return albumRepository.getAlbumsByPropositionTrue();
    }

    public List<Album> getPerformerAlbumsWithoutPropositions(Performer performer) {
        return albumRepository.getAlbumsByPerformerAndPropositionFalseOrderByYearOfPublicationDesc(performer);
    }

    public List<Album> getAlbumPropositionsAverageOrdered() {
        return albumRepository.getAlbumsByPropositionFalseOrderByAverageDesc();
    }

    public List<Album> getAlbumsByCategories(List<Category> categories) {
        return albumRepository.getAlbumsByCategoriesInAndPropositionFalseOrderByAverageDesc(categories);
    }

    public void removeAlbum(Long id) {
        albumRepository.delete(id);
    }

    public void confirmAlbum(Long id) {
        Album album = albumRepository.findOne(id);
        album.setProposition(false);
        albumRepository.save(album);
    }

}
