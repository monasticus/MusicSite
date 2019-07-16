package com.musicsite.repository;

import com.musicsite.entity.Album;
import com.musicsite.entity.Category;
import com.musicsite.entity.Performer;
import com.musicsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> getAlbumsByPerformer(Performer performer);
    List<Album> getAlbumsByPerformerOrderByYearOfPublicationDesc(Performer performer);
    List<Album> getAlbumsByNameIgnoreCase(String name);
    Album getFirstAlbumByNameIgnoreCase(String name);
    List<Album> getAlbumsByPropositionTrue();
    List<Album> getAlbumsByPropositionFalseOrderByAverageDesc();
    List<Album> getAlbumsByCategoriesAndPropositionFalseOrderByAverageDesc(Category category);
    List<Album> getAlbumsByCategoriesAndPropositionFalseOrderByAverageDesc(List<Category> categories);
}
