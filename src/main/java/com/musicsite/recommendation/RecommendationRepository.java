package com.musicsite.recommendation;

import com.musicsite.album.Album;
import com.musicsite.performer.Performer;
import com.musicsite.rating.Rating;
import com.musicsite.track.Track;
import com.musicsite.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    Recommendation getRecommendationByUserAndPerformer(User user, Performer performer);
    Recommendation getRecommendationByUserIdAndPerformerId(Long userId, Long performerId);
    Recommendation getRecommendationByUserAndAlbum(User user, Album album);
    Recommendation getRecommendationByUserIdAndAlbumId(Long userId, Long albumId);
    Recommendation getRecommendationByUserAndTrack(User user, Track track);
    Recommendation getRecommendationByUserIdAndTrackId(Long userId, Long trackId);
    int countRecommendationsByPerformerId(Long id);
    int countRecommendationsByAlbumId(Long id);
    int countRecommendationsByTrackId(Long id);
}
