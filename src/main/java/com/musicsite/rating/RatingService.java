package com.musicsite.rating;

import com.musicsite.album.Album;
import com.musicsite.entity.Ens;
import com.musicsite.performer.Performer;
import com.musicsite.track.Track;
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

    public Integer countRatings (Ens ens){
        if (ens instanceof Performer)
            return ratingRepository.countRatingsByPerformerId(ens.getId());
        else if (ens instanceof Album)
            return ratingRepository.countRatingsByAlbumId(ens.getId());
        else if (ens instanceof Track)
            return ratingRepository.countRatingsByTrackId(ens.getId());
        else
            return null;
    }


}
