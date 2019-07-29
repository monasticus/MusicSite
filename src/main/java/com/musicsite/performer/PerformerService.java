package com.musicsite.performer;

import com.musicsite.album.Album;
import com.musicsite.album.AlbumRepository;
import com.musicsite.category.Category;
import com.musicsite.track.Track;
import com.musicsite.track.TrackRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
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

    public PerformerService(PerformerRepository performerRepository,
                            AlbumRepository albumRepository,
                            TrackRepository trackRepository) {

        this.performerRepository = performerRepository;
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
    }

    public Performer getPerformerById(Long id) {
        Performer performer = performerRepository.findOne(id);
        if (performer == null)
            return null;
        Hibernate.initialize(performer.getAlbums());
        performer.getAlbums().forEach(a -> Hibernate.initialize(a.getCategories()));
        Hibernate.initialize(performer.getTracks());
        Hibernate.initialize(performer.getRatings());
        Hibernate.initialize(performer.getComments());
        setPerformerCategories(performer);
        return performer;
    }

    public void save(Performer performer) {
        performerRepository.save(performer);
    }

    public Performer getPerformerByPseudonymSaute(String pseudonym) {
        return performerRepository.getFirstPerformerByPseudonymIgnoreCase(pseudonym);
    }

    public List<Performer> getPerformersByPseudonymSaute(String pseudonym) {
        return performerRepository.getPerformersByPseudonymIgnoreCase(pseudonym);
    }

    public List<Performer> getOnlyPerformers() {
        List<Performer> performers = performerRepository.getPerformersByPropositionFalseOrderByAverageDesc();
        performers.forEach(p -> Hibernate.initialize(p.getAlbums()));
        performers.forEach(p -> p.getAlbums().forEach(a -> Hibernate.initialize(a.getCategories())));
        performers.forEach(p -> Hibernate.initialize(p.getTracks()));
        performers.forEach(p -> Hibernate.initialize(p.getRatings()));
        performers.forEach(this::setPerformerCategories);
        return performers;
    }

    public List<Performer> getPerformersWithCategories() {
        return setPerformersCategories(getOnlyPerformers());
    }

    private List<Performer> setPerformersCategories(List<Performer> performers) {
        performers.forEach(this::setPerformerCategories);
        return performers;
    }

    private Performer setPerformerCategories(Performer performer) {
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
        List<Performer> propositions = performerRepository.getPerformersByPropositionTrue();
        propositions.forEach(p -> Hibernate.initialize(p.getAlbums()));
        propositions.forEach(p -> p.getAlbums().forEach(a -> Hibernate.initialize(a.getCategories())));
        propositions.forEach(p -> Hibernate.initialize(p.getTracks()));
        propositions.forEach(p -> Hibernate.initialize(p.getRatings()));
        return setPerformersCategories(propositions);
    }

    public List<Performer> getPerformersByQuery(String query) {
        List<Performer> performers = performerRepository.customGetPerformersByQuery(query);
        performers.forEach(p -> Hibernate.initialize(p.getAlbums()));
        performers.forEach(p -> p.getAlbums().forEach(a -> Hibernate.initialize(a.getCategories())));
        performers.forEach(p -> Hibernate.initialize(p.getTracks()));
        performers.forEach(p -> Hibernate.initialize(p.getRatings()));
        return setPerformersCategories(performers);
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
