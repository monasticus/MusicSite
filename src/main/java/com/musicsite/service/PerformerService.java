package com.musicsite.service;

import com.musicsite.entity.Performer;
import com.musicsite.entity.Rating;
import com.musicsite.entity.User;
import com.musicsite.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PerformerService {

    private PerformerRepository performerRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    public PerformerService(PerformerRepository performerRepository,
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

    public Performer getPerformer(Long id) {
        return performerRepository.findOne(id);
    }

    public List<Performer> getPerformers() {
        return performerRepository.findAll();
    }

    @Transactional(propagation = Propagation.NEVER)
    public void orderData(Performer performer) {
        if (performer.getAlbums().size() > 0) {
            performer.setAlbums(albumRepository.getAlbumsByPerformerOrderByYearOfPublicationDesc(performer));
            performer.getAlbums().forEach(a -> a.setName(StringUtils.capitalize(a.getName())));
        }

        if (performer.getTracks().size() > 0)
            performer.setTracks(trackRepository.getTracksByPerformerOrderByYearOfPublicationDesc(performer));
    }

    public void saveRating(Long userId, Long performerId, int rating) {
        User user = userRepository.findOne(userId);

        Performer performer = performerRepository.findOne(performerId);

        Rating userRating = ratingRepository.getRatingByUserAndPerformer(user, performer);

        if (userRating == null) {
            userRating = new Rating();
            userRating.setPerformer(performer);
            userRating.setUser(user);
        }

        userRating.setRating(rating);

        ratingRepository.save(userRating);
    }

    public void updatePerformerAverage(Long performerId) {
        Performer performer = performerRepository.findOne(performerId);
        performer.updateAverage();
        performerRepository.save(performer);
    }
}
