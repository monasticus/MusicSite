package com.musicsite.service;

import com.musicsite.entity.*;
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

    public Album getAlbum(Long id) {
        return albumRepository.findOne(id);
    }

    public void saveRating(Long userId, Long albumId, int rating) {
        User user = userRepository.findOne(userId);
        Album album = albumRepository.findOne(albumId);
        Rating userRating = ratingRepository.getRatingByUserAndAlbum(user, album);

        if (userRating == null) {
            userRating = new Rating();
            userRating.setAlbum(album);
            userRating.setUser(user);
        }

        userRating.setRating(rating);

        ratingRepository.save(userRating);
    }

    public void updateAlbumAverage(Long albumId) {
        Album album = albumRepository.findOne(albumId);
        album.updateAverage();
        albumRepository.save(album);
    }

    public List<Album> getAlbumsWithPropositions() {
        return albumRepository.findAll();
    }

    public List<Album> getOnlyAlbumPropositions() {
        return albumRepository.getAlbumsByPropositionTrue();
    }

    public List<Album> getPerformerAlbums(Performer performer, boolean value) {
        return albumRepository.getAlbumsByPerformerAndPropositionOrderByYearOfPublicationDesc(performer, value);
    }

    public List<Album> getAlbumPropositionsAverageOrdered() {
        return albumRepository.getAlbumsByPropositionFalseOrderByAverageDesc();
    }

    public List<Album> getAlbumsByCategories(List<Category> categories) {
        return albumRepository.getAlbumsByCategoriesInAndPropositionFalseOrderByAverageDesc(categories);
    }

    public List<Album> getAlbumsByQuery (String query) {
        return albumRepository.customGetAlbumsByQuery(query);
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
