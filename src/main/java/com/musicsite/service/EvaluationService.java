package com.musicsite.service;

import com.musicsite.favorite.FavoriteRepository;
import com.musicsite.rating.RatingRepository;
import com.musicsite.recommendation.RecommendationRepository;
import com.musicsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EvaluationService {

    private RatingRepository ratingRepository;
    private RecommendationRepository recommendationRepository;
    private FavoriteRepository favoriteRepository;
    private UserRepository userRepository;

    @Autowired
    public EvaluationService(RatingRepository ratingRepository, RecommendationRepository recommendationRepository, FavoriteRepository favoriteRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.recommendationRepository = recommendationRepository;
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
    }
}
