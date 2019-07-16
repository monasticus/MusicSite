package com.musicsite.service;

import com.musicsite.entity.Album;
import com.musicsite.entity.Performer;
import com.musicsite.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AlbumService {

    private PerformerRepository performerRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public AlbumService(PerformerRepository performerRepository,
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

    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    public List<Album> getPropositionAlbums() {
        return albumRepository.getAlbumsByPropositionTrue();
    }

    @Transactional(propagation = Propagation.NEVER)
    public List<Album> getCapitalizedAlbums() {
        List<Album> albums = new ArrayList<>(getPropositionAlbums());
        albums.forEach(a -> a.setName(StringUtils.capitalize(a.getName())));
        return albums;
    }

}
