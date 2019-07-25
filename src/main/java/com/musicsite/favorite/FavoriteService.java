package com.musicsite.favorite;

import com.musicsite.album.Album;
import com.musicsite.entity.Ens;
import com.musicsite.performer.Performer;
import com.musicsite.recommendation.Recommendation;
import com.musicsite.track.Track;
import com.musicsite.user.User;
import com.musicsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FavoriteService {
    private FavoriteRepository favoriteRepository;
    private UserRepository userRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
    }

    public void removePerformerRecommendation(Long userId, Long performerId) {
        favoriteRepository.delete(favoriteRepository.getFavoriteByUserIdAndPerformerId(userId, performerId));
    }

    public void removeAlbumRecommendation(Long userId, Long albumId) {
        favoriteRepository.delete(favoriteRepository.getFavoriteByUserIdAndAlbumId(userId, albumId));
    }

    public void removeTrackRecommendation(Long userId, Long trackId) {
        favoriteRepository.delete(favoriteRepository.getFavoriteByUserIdAndTrackId(userId, trackId));
    }

    public void setFavorite(Long userId, Ens ens) {
        User user = userRepository.findOne(userId);

        Favorite favorite = new Favorite();
        favorite.setUser(user);

        if (ens instanceof Performer)
            favorite.setPerformer((Performer) ens);
        else if (ens instanceof Album)
            favorite.setAlbum((Album) ens);
        else if (ens instanceof Track)
            favorite.setTrack((Track) ens);

        favoriteRepository.save(favorite);
    }


}
