package com.musicsite.controller;

import com.musicsite.album.Album;
import com.musicsite.album.AlbumService;
import com.musicsite.entity.Ens;
import com.musicsite.favorite.FavoriteService;
import com.musicsite.performer.Performer;
import com.musicsite.performer.PerformerService;
import com.musicsite.rating.RatingService;
import com.musicsite.recommendation.RecommendationService;
import com.musicsite.track.Track;
import com.musicsite.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class EvaluateController {

    private PerformerService performerService;
    private AlbumService albumService;
    private TrackService trackService;
    private RatingService ratingService;
    private RecommendationService recommendationService;
    private FavoriteService favoriteService;

    @Autowired
    public EvaluateController(PerformerService performerService,
                              AlbumService albumService,
                              TrackService trackService,
                              RatingService ratingService,
                              RecommendationService recommendationService,
                              FavoriteService favoriteService) {
        this.performerService = performerService;
        this.albumService = albumService;
        this.trackService = trackService;
        this.ratingService = ratingService;
        this.recommendationService = recommendationService;
        this.favoriteService = favoriteService;
    }

    @GetMapping("/{species:performer|album|track}/{ensId}/setRate/{rating}")
    public String rateEns(@PathVariable String species, @PathVariable Long ensId, @PathVariable int rating, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId == null)
            return "redirect:/login";

        Ens ens = getEns(species, ensId);
        ratingService.rateEns(userId, ens, rating);
        updateEnsAverage(ens);
        return "redirect:/" + species + "/".concat(String.valueOf(ensId));
    }

    @GetMapping("/{species:performer|album|track}/{ensId}/setRecomm")
    public String recommendEns(@PathVariable String species, @PathVariable Long ensId, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId == null)
            return "redirect:/login";

        Ens ens = getEns(species, ensId);
        recommendationService.toggleRecommendation(userId, ens);
        return "redirect:/" + species + "/".concat(String.valueOf(ensId));
    }

    @GetMapping("/{species:performer|album|track}/{ensId}/setFavorite")
    public String favoritePerformer(@PathVariable String species, @PathVariable Long ensId, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId == null)
            return "redirect:/login";

        Ens ens = getEns(species, ensId);
        favoriteService.toggleFavorite(userId, ens);
        return "redirect:/" + species + "/".concat(String.valueOf(ensId));
    }


    private Ens getEns(@PathVariable String species, @PathVariable Long ensId) {
        switch (species) {
            case "performer":
                return performerService.getPerformer(ensId);
            case "album":
                return albumService.getAlbum(ensId);
            case "track":
                return trackService.getTrack(ensId);
            default:
                return null;
        }
    }

    private void updateEnsAverage(Ens ens) {
        if (ens instanceof Performer)
            performerService.updatePerformerAverage(ens.getId());
        else if (ens instanceof Album)
            albumService.updateAlbumAverage(ens.getId());
        else if (ens instanceof Track)
            trackService.updateTrackAverage(ens.getId());
    }
}
