package com.musicsite.repository;

import com.musicsite.entity.Album;
import com.musicsite.entity.Category;
import com.musicsite.entity.Performer;
import com.musicsite.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> getTracksByNameIgnoreCase(String name);

    List<Track> getTracksByProposition(boolean value);

    List<Track> getTracksByPerformerOrderByYearOfPublicationDesc(Performer performer);

    List<Track> getTracksByAlbumAndPropositionOrderByOrdinalNum(Album album, boolean value);

    List<Track> getTracksByPerformerAndPropositionOrderByYearOfPublicationDesc(Performer performer, boolean value);

    List<Track> getTracksByPropositionOrderByAverageDesc(boolean value);

    List<Track> getDistinctTracksByCategoryInAndPropositionOrderByAverageDesc(List<Category> categories, boolean value);

    @Query("SELECT t.id FROM Track t")
    List<Long> getIdList();

//    @Query(value = "SELECT id FROM tracks ORDER BY id DESC LIMIT 1", nativeQuery = true)
//    Long getLastId();

    @Query("SELECT t FROM Track t WHERE LOWER(t.name) like LOWER(concat('%', :part, '%') ) AND t.proposition = 0")
    List<Track> customGetTracksByQuery(@Param("part") String part);

}
