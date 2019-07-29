package com.musicsite.album;

import com.musicsite.category.Category;
import com.musicsite.performer.Performer;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumService {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public void save(Album album) {
        albumRepository.save(album);
    }

    public void removeAlbum(Long id) {
        albumRepository.delete(id);
    }

    public void confirmAlbum(Long id) {
        Album album = albumRepository.findOne(id);
        album.setProposition(false);
        albumRepository.save(album);
    }

    public void updateAlbumAverage(Long albumid) {
        Album album = albumRepository.findOne(albumid);
        album.updateAverage();
        albumRepository.save(album);
    }

    public Album getAlbum(Long id) {
        Album album = albumRepository.findOne(id);
        if (album == null)
            return null;

        return getAlbum(album);
    }

    public List<Album> getAlbumsByNameSaute(String name) {
        return albumRepository.getAlbumsByNameIgnoreCase(name);
    }

    public List<Album> getAlbumsByPropositions(boolean value) {
        List<Album> albums = albumRepository.getAlbumsByProposition(value);
        return getAlbums(albums);
    }

    public List<Album> getAlbumsByPerformerAndPropositionOrderByYear(Performer performer, boolean value) {
        List<Album> albums = albumRepository.getAlbumsByPerformerAndPropositionOrderByYearOfPublicationDesc(performer, value);
        return getAlbums(albums);
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


    private Album getAlbum(Album album) {
        Hibernate.initialize(album.getCategories());
        Hibernate.initialize(album.getRatings());
        Hibernate.initialize(album.getTracks());
        Hibernate.initialize(album.getComments());
        return album;
    }

    private List<Album> getAlbums(List<Album> albums) {
        albums.forEach(this::getAlbum);
        return albums;
    }

}
