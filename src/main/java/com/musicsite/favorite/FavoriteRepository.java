package com.musicsite.favorite;

import com.musicsite.album.Album;
import com.musicsite.performer.Performer;
import com.musicsite.rating.Rating;
import com.musicsite.track.Track;
import com.musicsite.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Rating, Long> {
    Rating getFavoriteByUserAndPerformer(User user, Performer performer);
    Rating getFavoriteByUserIdAndPerformerId(Long userId, Long performerId);
    Rating getFavoriteByUserAndAlbum(User user, Album album);
    Rating getFavoriteByUserIdAndAlbumId(Long userId, Long albumId);
    Rating getFavoriteByUserAndTrack(User user, Track track);
    Rating getFavoriteByUserIdAndTrackId(Long userId, Long trackId);
}
