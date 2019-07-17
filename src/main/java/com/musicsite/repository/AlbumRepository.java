package com.musicsite.repository;

import com.musicsite.entity.Album;
import com.musicsite.entity.Category;
import com.musicsite.entity.Performer;
import com.musicsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> getAlbumsByPerformer(Performer performer);
    List<Album> getAlbumsByPerformerOrderByYearOfPublicationDesc(Performer performer);
    List<Album> getAlbumsByPerformerAndPropositionFalseOrderByYearOfPublicationDesc(Performer performer);
    List<Album> getAlbumsByNameIgnoreCase(String name);
    Album getFirstAlbumByNameIgnoreCase(String name);
    List<Album> getAlbumsByPropositionTrue();
    List<Album> getAlbumsByPropositionFalseOrderByAverageDesc();
    List<Album> getAlbumsByCategoriesAndPropositionFalseOrderByAverageDesc(Category category);
    List<Album> getAlbumsByCategoriesInAndPropositionFalseOrderByAverageDesc(List<Category> categories);


    @Query("SELECT a FROM Album a WHERE LOWER(a.name) like LOWER(concat('%', :part, '%') ) AND a.proposition = 0")
    List<Album> customGetAlbumsByQuery(@Param("part") String part);
}
