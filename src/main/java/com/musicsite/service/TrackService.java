package com.musicsite.service;

import com.musicsite.entity.Album;
import com.musicsite.entity.Category;
import com.musicsite.entity.Performer;
import com.musicsite.entity.Track;
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
}
