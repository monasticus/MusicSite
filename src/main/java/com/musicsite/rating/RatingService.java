package com.musicsite.rating;

import com.musicsite.album.Album;
import com.musicsite.entity.Ens;
import com.musicsite.performer.Performer;
import com.musicsite.track.Track;
import com.musicsite.user.User;
import com.musicsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RatingService {
    private RatingRepository ratingRepository;
    private UserRepository userRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    public void rateEns(Long userId, Ens ens, int rating) {
        Rating userRating = getEnsUserRating(ens, userId);

        if (userRating != null && userRating.getRating() == rating)
            removeEnsRating(userId, ens);
        else
            setRating(userId, ens, rating);
    }

    public void setRating(Long userId, Ens ens, int rating) {
        Rating userRating = getEnsUserRating(ens, userId);

        if (userRating == null) {
            userRating = new Rating();
            userRating.setUser(userRepository.findOne(userId));
            userRating.setEns(ens);
        }

        userRating.setRating(rating);

        ratingRepository.save(userRating);
    }

    public void removeEnsRating(Long userId, Ens ens) {
        if (ens instanceof Performer)
            ratingRepository.delete(ratingRepository.getRatingByUserIdAndPerformerId(userId, ens.getId()));
        else if (ens instanceof Album)
            ratingRepository.delete(ratingRepository.getRatingByUserIdAndAlbumId(userId, ens.getId()));
        else if (ens instanceof Track)
            ratingRepository.delete(ratingRepository.getRatingByUserIdAndTrackId(userId, ens.getId()));
    }

    public Integer countRatings(Ens ens) {
        if (ens instanceof Performer)
            return ratingRepository.countRatingsByPerformerId(ens.getId());
        else if (ens instanceof Album)
            return ratingRepository.countRatingsByAlbumId(ens.getId());
        else if (ens instanceof Track)
            return ratingRepository.countRatingsByTrackId(ens.getId());
        else
            return null;
    }

    public Rating getEnsUserRating(Ens ens, Long userId) {
        User user = userRepository.findOne(userId);

        if (ens instanceof Performer)
            return ratingRepository.getRatingByUserAndPerformer(user, (Performer) ens);
        else if (ens instanceof Album)
            return ratingRepository.getRatingByUserAndAlbum(user, (Album) ens);
        else if (ens instanceof Track)
            return ratingRepository.getRatingByUserAndTrack(user, (Track) ens);
        else
            return null;
    }

}
