package com.musicsite.album;

import com.musicsite.category.Category;
import com.musicsite.performer.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> getAlbumsByPerformerOrderByYearOfPublicationDesc(Performer performer);

    List<Album> getAlbumsByPerformerAndPropositionOrderByYearOfPublicationDesc(Performer performer, boolean value);

    List<Album> getAlbumsByNameIgnoreCase(String name);

    List<Album> getAlbumsByProposition(boolean value);

    List<Album> getAlbumsByPropositionOrderByAverageDesc(boolean value);

    List<Album> getDistinctAlbumsByCategoriesInAndPropositionOrderByAverageDesc(List<Category> categories, boolean vallue);


    @Query("SELECT a FROM Album a WHERE LOWER(a.name) like LOWER(concat('%', :part, '%') ) AND a.proposition = 0")
    List<Album> customGetAlbumsByQuery(@Param("part") String part);
}
