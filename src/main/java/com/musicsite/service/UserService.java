package com.musicsite.service;

import com.musicsite.entity.Performer;
import com.musicsite.entity.Rating;
import com.musicsite.entity.User;
import com.musicsite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private PerformerRepository performerRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public UserService(PerformerRepository performerRepository,
                            AlbumRepository albumRepository,
                            TrackRepository trackRepository,
                            UserRepository userRepository,
                            RatingRepository ratingRepository) {

        this.performerRepository = performerRepository;
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    public int getPerformerUserRating (Long userId, Performer performer) {
        Rating rating = ratingRepository.getRatingByUserAndPerformer(getUser(userId), performer);
        if (rating == null)
            return 0;
        else
            return rating.getRating();
    }
}
