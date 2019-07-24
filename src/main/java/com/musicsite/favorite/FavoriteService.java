package com.musicsite.favorite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FavoriteService {
    private FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
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


}
