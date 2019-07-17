package com.musicsite.service;

import com.musicsite.entity.*;
import com.musicsite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrackService {

    private PerformerRepository performerRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public TrackService(PerformerRepository performerRepository,
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

    public Track getTrack(Long id) {
        return trackRepository.findOne(id);
    }

    public List<Track> getTracksWithPropositions() {
        return trackRepository.findAll();
    }

    public List<Track> getOnlyTracks() {
        return trackRepository.getTracksByPropositionFalseOrderByAverageDesc();
    }

    public List<Track> getPerformerTracksWithoutPropositions(Performer performer) {
        return trackRepository.getTracksByPerformerAndPropositionFalseOrderByYearOfPublicationDesc(performer);
    }

    public List<Track> getTracksByCategories(List<Category> categories) {
        return trackRepository.getTracksByCategoryInAndPropositionFalseOrderByAverageDesc(categories);
    }

    public List<Track> getOnlyTrackPropositions() {
        return trackRepository.getTracksByPropositionTrue();
    }

    public List<Track> getTracksByQuery (String query) {
        return trackRepository.customGetTracksByQuery(query);
    }

    public void removeTrack(Long id) {
        trackRepository.delete(id);
    }

    public void confirmTrack(Long id) {
        Track track = trackRepository.findOne(id);
        track.setProposition(false);
        trackRepository.save(track);
    }

    public void saveRating(Long userId, Long trackId, int rating) {
        User user = userRepository.findOne(userId);
        Track track = trackRepository.findOne(trackId);
        Rating userRating = ratingRepository.getRatingByUserAndTrack(user, track);

        if (userRating == null) {
            userRating = new Rating();
            userRating.setTrack(track);
            userRating.setUser(user);
        }

        userRating.setRating(rating);

        ratingRepository.save(userRating);
    }

    public void updateTrackAverage(Long trackId) {
        Track track = trackRepository.findOne(trackId);
        track.updateAverage();
        trackRepository.save(track);
    }
}
