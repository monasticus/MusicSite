package com.musicsite.repository;

import com.musicsite.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating getRatingByUserAndPerformer(User user, Performer performer);
    Rating getRatingByUserAndAlbum(User user, Album album);
    Rating getRatingByUserAndTrack(User user, Track track);
}
