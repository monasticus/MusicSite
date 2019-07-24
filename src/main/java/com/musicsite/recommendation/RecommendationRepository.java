package com.musicsite.recommendation;

import com.musicsite.album.Album;
import com.musicsite.performer.Performer;
import com.musicsite.rating.Rating;
import com.musicsite.track.Track;
import com.musicsite.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Rating, Long> {
    Rating getRecommendationByUserAndPerformer(User user, Performer performer);
    Rating getRecommendationByUserIdAndPerformerId(Long userId, Long performerId);
    Rating getRecommendationByUserAndAlbum(User user, Album album);
    Rating getRecommendationByUserIdAndAlbumId(Long userId, Long albumId);
    Rating getRecommendationByUserAndTrack(User user, Track track);
    Rating getRecommendationByUserIdAndTrackId(Long userId, Long trackId);
}
