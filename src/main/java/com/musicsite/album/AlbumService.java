package com.musicsite.album;

import com.musicsite.album.Album;
import com.musicsite.album.AlbumRepository;
import com.musicsite.category.Category;
import com.musicsite.performer.Performer;
import com.musicsite.rating.Rating;
import com.musicsite.rating.RatingRepository;
import com.musicsite.user.User;
import com.musicsite.user.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        if (album == null)
            return null;
        Hibernate.initialize(album.getCategories());
        Hibernate.initialize(album.getRatings());
        Hibernate.initialize(album.getTracks());
        Hibernate.initialize(album.getComments());
        return album;
    }

    public void save(Album album) {
        albumRepository.save(album);
    }

    public List<Album> getAlbumsByNameSaute(String name) {
        return albumRepository.getAlbumsByNameIgnoreCase(name);
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
        albums.forEach(a -> Hibernate.initialize(a.getComments()));
        return albums;
    }

    public List<Album> getAlbumsByPerformerAndPropositionOrderByYear(Performer performer, boolean value) {
        List<Album> albums = albumRepository.getAlbumsByPerformerAndPropositionOrderByYearOfPublicationDesc(performer, value);
        albums.forEach(a -> Hibernate.initialize(a.getCategories()));
        albums.forEach(a -> Hibernate.initialize(a.getRatings()));
        albums.forEach(a -> Hibernate.initialize(a.getTracks()));
        albums.forEach(a -> Hibernate.initialize(a.getComments()));
        return albums;
    }

    public List<Album> getAlbumsByPropositionOrderByAverage(boolean value) {
        List<Album> albums = albumRepository.getAlbumsByPropositionOrderByAverageDesc(value);
        albums.forEach(a -> Hibernate.initialize(a.getCategories()));
        albums.forEach(a -> Hibernate.initialize(a.getRatings()));
        albums.forEach(a -> Hibernate.initialize(a.getTracks()));
        albums.forEach(a -> Hibernate.initialize(a.getComments()));
        return albums;
    }

    public List<Album> getAlbumsByCategoriesAndPropositionsOrderByAverage(List<Category> categories, boolean value) {
        List<Album> albums = albumRepository.getDistinctAlbumsByCategoriesInAndPropositionOrderByAverageDesc(categories, value);
        albums.forEach(a -> Hibernate.initialize(a.getCategories()));
        albums.forEach(a -> Hibernate.initialize(a.getRatings()));
        albums.forEach(a -> Hibernate.initialize(a.getTracks()));
        albums.forEach(a -> Hibernate.initialize(a.getComments()));
        return albums;
    }

    public List<Album> getAlbumsByQuery(String query) {
        List<Album> albums = albumRepository.customGetAlbumsByQuery(query);
        albums.forEach(a -> Hibernate.initialize(a.getCategories()));
        albums.forEach(a -> Hibernate.initialize(a.getRatings()));
        albums.forEach(a -> Hibernate.initialize(a.getTracks()));
        albums.forEach(a -> Hibernate.initialize(a.getComments()));
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
