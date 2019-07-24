package com.musicsite.repository;

import com.musicsite.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating getRatingByUserAndPerformer(User user, Performer performer);
    Rating getRatingByUserIdAndPerformerId(Long userId, Long performerId);
    Rating getRatingByUserAndAlbum(User user, Album album);
    Rating getRatingByUserIdAndAlbumId(Long userId, Long albumId);
    Rating getRatingByUserAndTrack(User user, Track track);
    Rating getRatingByUserIdAndTrackId(Long userId, Long trackId);
}
