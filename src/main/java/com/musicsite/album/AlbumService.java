package com.musicsite.album;

import com.musicsite.category.Category;
import com.musicsite.performer.Performer;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumService {

    private AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
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

    public void updateAlbumAverage(Long albumid) {
        Album album = albumRepository.findOne(albumid);
        album.updateAverage();
        albumRepository.save(album);
    }

    public List<Album> getAlbumsByPropositions(boolean value) {
        List<Album> albums = albumRepository.getAlbumsByProposition(value);
        return getAlbums(albums);
    }

    public List<Album> getAlbumsByPerformerAndPropositionOrderByYear(Performer performer, boolean value) {
        List<Album> albums = albumRepository.getAlbumsByPerformerAndPropositionOrderByYearOfPublicationDesc(performer, value);
        return getAlbums(albums);
    }

    private List<Album> getAlbums(List<Album> albums) {
        albums.forEach(a -> Hibernate.initialize(a.getCategories()));
        albums.forEach(a -> Hibernate.initialize(a.getRatings()));
        albums.forEach(a -> Hibernate.initialize(a.getTracks()));
        albums.forEach(a -> Hibernate.initialize(a.getComments()));
        return albums;
    }

    public List<Album> getAlbumsByPropositionOrderByAverage(boolean value) {
        List<Album> albums = albumRepository.getAlbumsByPropositionOrderByAverageDesc(value);
        return getAlbums(albums);
    }

    public List<Album> getAlbumsByCategoriesAndPropositionsOrderByAverage(List<Category> categories, boolean value) {
        List<Album> albums = albumRepository.getDistinctAlbumsByCategoriesInAndPropositionOrderByAverageDesc(categories, value);
        return getAlbums(albums);
    }

    public List<Album> getAlbumsByQuery(String query) {
        List<Album> albums = albumRepository.customGetAlbumsByQuery(query);
        return getAlbums(albums);
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
