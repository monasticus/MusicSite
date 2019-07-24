package com.musicsite.rating;

import com.musicsite.rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RatingService {
    private RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void removePerformerRating(Long userId, Long performerId) {
        ratingRepository.delete(ratingRepository.getRatingByUserIdAndPerformerId(userId, performerId));
    }

    public void removeAlbumRating(Long userId, Long albumId) {
        ratingRepository.delete(ratingRepository.getRatingByUserIdAndAlbumId(userId, albumId));
    }

    public void removeTrackRating(Long userId, Long trackId) {
        ratingRepository.delete(ratingRepository.getRatingByUserIdAndTrackId(userId, trackId));
    }


}
