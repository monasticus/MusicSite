package com.musicsite.service;

import com.musicsite.entity.*;
import com.musicsite.repository.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AlbumService {

    private AlbumRepository albumRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository,
                        UserRepository userRepository,
                        RatingRepository ratingRepository) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    public Album getAlbum(Long id) {
        Album album = albumRepository.findOne(id);
        Hibernate.initialize(album.getCategories());
        Hibernate.initialize(album.getRatings());
        Hibernate.initialize(album.getTracks());
        return album;
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

    public List<Album> getAlbumsByPropositions(boolean value) {
        List<Album> albums = albumRepository.getAlbumsByProposition(value);
        albums.forEach(a -> Hibernate.initialize(a.getCategories()));
        albums.forEach(a -> Hibernate.initialize(a.getRatings()));
        albums.forEach(a -> Hibernate.initialize(a.getTracks()));
        return albums;
    }

    public List<Album> getAlbumsByPerformerAndPropositionOrderByYear(Performer performer, boolean value) {
        List<Album> albums = albumRepository.getAlbumsByPerformerAndPropositionOrderByYearOfPublicationDesc(performer, value);
        albums.forEach(a -> Hibernate.initialize(a.getCategories()));
        albums.forEach(a -> Hibernate.initialize(a.getRatings()));
        albums.forEach(a -> Hibernate.initialize(a.getTracks()));
        return albums;
    }

    public List<Album> getAlbumsByPropositionOrderByAverage(boolean value) {
        List<Album> albums = albumRepository.getAlbumsByPropositionOrderByAverageDesc(value);
        albums.forEach(a -> Hibernate.initialize(a.getCategories()));
        albums.forEach(a -> Hibernate.initialize(a.getRatings()));
        albums.forEach(a -> Hibernate.initialize(a.getTracks()));
        return albums;
    }

    public List<Album> getAlbumsByCategoriesAndPropositionsOrderByAverage(List<Category> categories, boolean value) {
        List<Album> albums = albumRepository.getDistinctAlbumsByCategoriesInAndPropositionOrderByAverageDesc(categories, value);
        albums.forEach(a -> Hibernate.initialize(a.getCategories()));
        albums.forEach(a -> Hibernate.initialize(a.getRatings()));
        albums.forEach(a -> Hibernate.initialize(a.getTracks()));
        return albums;
    }

    public List<Album> getAlbumsByQuery(String query) {
        List<Album> albums = albumRepository.customGetAlbumsByQuery(query);
        albums.forEach(a -> Hibernate.initialize(a.getCategories()));
        albums.forEach(a -> Hibernate.initialize(a.getRatings()));
        albums.forEach(a -> Hibernate.initialize(a.getTracks()));
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
