package com.musicsite.track;

import com.musicsite.album.Album;
import com.musicsite.category.Category;
import com.musicsite.performer.Performer;
import org.hibernate.Hibernate;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrackService {

    private TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {

        this.trackRepository = trackRepository;
    }

    public Track getTrack(Long id) {
        Track track = trackRepository.findOne(id);
        if (track == null)
            return null;

        Hibernate.initialize(track.getRatings());
        Hibernate.initialize(track.getComments());
        if (track.getAlbum() != null) {
            Hibernate.initialize(track.getAlbum().getRatings());
            Hibernate.initialize(track.getAlbum().getCategories());
            Hibernate.initialize(track.getAlbum().getComments());
        }

        return track;
    }

    public void save(Track track) {
        trackRepository.save(track);
    }

    public List<Track> getRandomTracks(int bound) {
        List<Track> tracks = new ArrayList<>();
        List<Long> idList = trackRepository.getIdList();
        Random random = new Random();

        while (tracks.size() < bound) {
            Long attempt = idList.get(random.nextInt(idList.size()));
            Track track = trackRepository.findOne(attempt);
            if (!tracks.contains(track))
                tracks.add(track);
        }

        tracks.forEach(t -> Hibernate.initialize(t.getRatings()));

        return tracks;
    }

    public List<Track> getTracksByNameSaute(String name) {
        return trackRepository.getTracksByNameIgnoreCase(name);
    }

    public List<Track> getTracksByPropositions(boolean value) {
        List<Track> tracks = trackRepository.getTracksByProposition(value);
        tracks.forEach(p -> Hibernate.initialize(p.getRatings()));
        return tracks;
    }

    public List<Track> getTracksByPropositionsOrderByAverage(boolean value) {
        List<Track> tracks = trackRepository.getTracksByPropositionOrderByAverageDesc(value);
        tracks.forEach(p -> Hibernate.initialize(p.getRatings()));
        return tracks;
    }

    public List<Track> getTracksByPerformerAndPropositionsOrderByYear(Performer performer, boolean value) {
        List<Track> tracks = trackRepository.getTracksByPerformerAndPropositionOrderByYearOfPublicationDesc(performer, value);
        tracks.forEach(p -> Hibernate.initialize(p.getRatings()));
        return tracks;
    }

    public List<Track> getTracksByAlbumAndPropositionsOrderByYearSaute(Album album, boolean value) {
        return trackRepository.getTracksByAlbumAndPropositionOrderByOrdinalNum(album, value);
    }


    public List<Track> getTracksByCategoriesAndPropositionOrderByAverage(List<Category> categories, boolean value) {
        List<Track> tracks = trackRepository.getDistinctTracksByCategoryInAndPropositionOrderByAverageDesc(categories, value).stream().distinct().collect(Collectors.toList());
        tracks.forEach(p -> Hibernate.initialize(p.getRatings()));
        return tracks;
    }

    public List<Track> getTracksByQuery(String query) {
        List<Track> tracks = trackRepository.customGetTracksByQuery(query);
        tracks.forEach(p -> Hibernate.initialize(p.getRatings()));
        return tracks;
    }

    public void removeTrack(Long id) {
        trackRepository.delete(id);
    }

    public void confirmTrack(Long id) {
        Track track = trackRepository.findOne(id);
        track.setProposition(false);
        trackRepository.save(track);
    }

    public void updateTrackAverage(Long trackId) {
        Track track = trackRepository.findOne(trackId);
        track.updateAverage();
        trackRepository.save(track);
    }

    public String getYoutubeURL(Long id) {
        Track track = trackRepository.findOne(id);
        String websiteURL = "https://www.youtube.com/results?search_query=";

        websiteURL = websiteURL.concat(track.getPerformer().getPseudonym().replaceAll(" ", "+")).concat("+");
        websiteURL = websiteURL.concat(track.getName().replaceAll(" ", "+"));
        if (track.getAlbum() != null && track.getAlbum().getName().equals(track.getName()))
            websiteURL = websiteURL.concat("+track");

        Connection connect = Jsoup.connect(websiteURL);

        String result = new String();
        try {
            Document doc = connect.get();
            Elements links = doc.select("ol.item-section>li>div>div>div>a");
            result = links.get(0).attr("href");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.replace("/watch?v=", "");
    }
}
