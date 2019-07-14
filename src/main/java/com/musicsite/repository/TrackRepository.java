package com.musicsite.repository;

import com.musicsite.entity.Album;
import com.musicsite.entity.Performer;
import com.musicsite.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> getTracksByName(String name);
    List<Track> getTracksByAlbum(Album album);
    List<Track> getTracksByPerformer(Performer performer);
}
