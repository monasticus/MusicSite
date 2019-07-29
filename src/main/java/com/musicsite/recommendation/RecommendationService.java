package com.musicsite.recommendation;

import com.musicsite.album.Album;
import com.musicsite.entity.Ens;
import com.musicsite.performer.Performer;
import com.musicsite.rating.Rating;
import com.musicsite.rating.RatingRepository;
import com.musicsite.track.Track;
import com.musicsite.user.User;
import com.musicsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RecommendationService {
    private RecommendationRepository recommendationRepository;
    private UserRepository userRepository;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository, UserRepository userRepository) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
    }

    public void toggleRecommendation(Long userId, Ens ens) {
        if (getEnsUserRecommendation(userId, ens))
            removeEnsRecommendation(userId, ens);
        else
            setRecommendation(userId, ens);
    }

    public void setRecommendation(Long userId, Ens ens) {
        User user = userRepository.findOne(userId);

        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);

        if (ens instanceof Performer)
            recommendation.setPerformer((Performer) ens);
        else if (ens instanceof Album)
            recommendation.setAlbum((Album) ens);
        else if (ens instanceof Track)
            recommendation.setTrack((Track) ens);

        recommendationRepository.save(recommendation);
    }

    public void removeEnsRecommendation(Long userId, Ens ens) {
        if (ens instanceof Performer)
            recommendationRepository.delete(recommendationRepository.getRecommendationByUserIdAndPerformerId(userId, ens.getId()));
        else if (ens instanceof Album)
            recommendationRepository.delete(recommendationRepository.getRecommendationByUserIdAndAlbumId(userId, ens.getId()));
        else if (ens instanceof Track)
            recommendationRepository.delete(recommendationRepository.getRecommendationByUserIdAndTrackId(userId, ens.getId()));
    }

    public Integer countRecommend (Ens ens){
        if (ens instanceof Performer)
            return recommendationRepository.countRecommendationsByPerformerId(ens.getId());
        else if (ens instanceof Album)
            return recommendationRepository.countRecommendationsByAlbumId(ens.getId());
        else if (ens instanceof Track)
            return recommendationRepository.countRecommendationsByTrackId(ens.getId());
        else
            return null;
    }

    public boolean getEnsUserRecommendation(Long userId, Ens ens) {
        Recommendation recommendation = null;
        User user = userRepository.findOne(userId);

        if (ens instanceof Performer)
            recommendation = recommendationRepository.getRecommendationByUserAndPerformer(user, (Performer) ens);
        else if (ens instanceof Album)
            recommendation = recommendationRepository.getRecommendationByUserAndAlbum(user, (Album) ens);
        else if (ens instanceof Track)
            recommendation = recommendationRepository.getRecommendationByUserAndTrack(user, (Track) ens);


        return recommendation != null;
    }
}
