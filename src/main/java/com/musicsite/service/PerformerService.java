package com.musicsite.service;

import com.musicsite.entity.*;
import com.musicsite.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PerformerService {

    private PerformerRepository performerRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    @Autowired
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

    public List<Performer> getPerformersWithPropositions() {
        return performerRepository.findAll();
    }

    public List<Performer> getOnlyPerformers() {
        return performerRepository.getPerformersByPropositionFalseOrderByAverageDesc();
    }

    public List<Performer> getPerformersWithCategories() {
        return setPerformersCategories(performerRepository.getPerformersByPropositionFalseOrderByAverageDesc());
    }

    public List<Performer> setPerformersCategories(List<Performer> performers) {
        performers.forEach(this::setPerformerCategories);
        return performers;
    }

    public Performer setPerformerCategories(Performer performer) {
        List<Category> categories = new ArrayList<>();

        for (Album album : performer.getAlbums()) {
            for (Category c : album.getCategories()) {
                if (!categories.contains(c)) {
                    categories.add(c);
                }
            }
        }

        for (Track track : performer.getTracks()) {
            Category category = track.getCategory();
            if (!categories.contains(category))
                categories.add(category);

        }

        performer.setCategories(categories);
        return performer;
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

    public List<Performer> getPerformersByCategories(List<Category> categories) {

        List<Performer> performers = getOnlyPerformers()
                .stream()
                .filter(p -> p.getAlbums()
                        .stream()
                        .anyMatch(a -> a.getCategories()
                                .stream()
                                .anyMatch(c1 -> categories.stream().anyMatch(c2 -> c1.getId().equals(c2.getId()))))
                        ||
                        p.getTracks()
                                .stream()
                                .anyMatch(t -> categories.stream().anyMatch(c -> t.getCategory().getId().equals(c.getId()))))
                .collect(Collectors.toList());
        setPerformersCategories(performers);
        return performers;
    }

    public List<Performer> getOnlyPerformerPropositions() {
        return performerRepository.getPerformersByPropositionTrue();
    }

    public void removePerformer(Long id) {
        performerRepository.delete(id);
    }

    public void confirmPerformer(Long id) {
        Performer performer = performerRepository.findOne(id);
        performer.setProposition(false);
        performerRepository.save(performer);
    }
}
