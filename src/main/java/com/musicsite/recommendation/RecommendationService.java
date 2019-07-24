package com.musicsite.recommendation;

import com.musicsite.rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RecommendationService {
    private RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public void removePerformerRecommendation(Long userId, Long performerId) {
        recommendationRepository.delete(recommendationRepository.getRecommendationByUserIdAndPerformerId(userId, performerId));
    }

    public void removeAlbumRecommendation(Long userId, Long albumId) {
        recommendationRepository.delete(recommendationRepository.getRecommendationByUserIdAndAlbumId(userId, albumId));
    }

    public void removeTrackRecommendation(Long userId, Long trackId) {
        recommendationRepository.delete(recommendationRepository.getRecommendationByUserIdAndTrackId(userId, trackId));
    }


}
