package com.musicsite.favorite;

import com.musicsite.album.Album;
import com.musicsite.performer.Performer;
import com.musicsite.rating.Rating;
import com.musicsite.track.Track;
import com.musicsite.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite getFavoriteByUserAndPerformer(User user, Performer performer);
    Favorite getFavoriteByUserIdAndPerformerId(Long userId, Long performerId);
    Favorite getFavoriteByUserAndAlbum(User user, Album album);
    Favorite getFavoriteByUserIdAndAlbumId(Long userId, Long albumId);
    Favorite getFavoriteByUserAndTrack(User user, Track track);
    Favorite getFavoriteByUserIdAndTrackId(Long userId, Long trackId);
}
