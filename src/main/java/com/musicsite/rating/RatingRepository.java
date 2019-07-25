package com.musicsite.rating;

import com.musicsite.album.Album;
import com.musicsite.performer.Performer;
import com.musicsite.track.Track;
import com.musicsite.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating getRatingByUserAndPerformer(User user, Performer performer);
    Rating getRatingByUserIdAndPerformerId(Long userId, Long performerId);
    Rating getRatingByUserAndAlbum(User user, Album album);
    Rating getRatingByUserIdAndAlbumId(Long userId, Long albumId);
    Rating getRatingByUserAndTrack(User user, Track track);
    Rating getRatingByUserIdAndTrackId(Long userId, Long trackId);
    int countRatingsByPerformerId(Long id);
    int countRatingsByAlbumId(Long id);
    int countRatingsByTrackId(Long id);
}
