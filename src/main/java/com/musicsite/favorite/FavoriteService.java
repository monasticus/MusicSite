package com.musicsite.favorite;

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
public class FavoriteService {
    private FavoriteRepository favoriteRepository;
    private UserRepository userRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
    }

    public void removeEnsFavorite(Long userId, Ens ens) {
        if (ens instanceof Performer)
            favoriteRepository.delete(favoriteRepository.getFavoriteByUserIdAndPerformerId(userId, ens.getId()));
        else if (ens instanceof Album)
            favoriteRepository.delete(favoriteRepository.getFavoriteByUserIdAndAlbumId(userId, ens.getId()));
        else if (ens instanceof Track)
            favoriteRepository.delete(favoriteRepository.getFavoriteByUserIdAndTrackId(userId, ens.getId()));
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
