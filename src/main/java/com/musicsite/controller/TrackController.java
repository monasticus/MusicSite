package com.musicsite.controller;

import com.musicsite.entity.Album;
import com.musicsite.entity.Track;
import com.musicsite.service.AlbumService;
import com.musicsite.service.RatingService;
import com.musicsite.service.TrackService;
import com.musicsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/track")
public class TrackController {

    private UserService userService;
    private TrackService trackService;
    private RatingService ratingService;

    @Autowired
    public TrackController(UserService userService,
                           TrackService trackService,
                           RatingService ratingService) {
        this.userService = userService;
        this.trackService = trackService;
        this.ratingService = ratingService;
    }


    @GetMapping("/{id}")
    public String showForm(@PathVariable Long id, Model model, HttpSession session) {
        Track track = trackService.getTrack(id);
        if (track == null || track.isProposition())
            return "main/blank";

        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId != null)
            model.addAttribute("userTrackRating", userService.getTrackUserRating(userId, track));


        String hyperlink = trackService.getYoutubeURL(id);
        model.addAttribute("trackHyperlink", hyperlink);

        model.addAttribute("track", track);

        return "main/track";
    }

    @GetMapping("/{trackId}/setRate/{rating}")
    public String ratePerformer(@PathVariable Long trackId, @PathVariable int rating, HttpSession session) {

        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null)
            return "redirect:/login";

        if (userService.getTrackUserRating(userId, trackService.getTrack(trackId)) == (rating))
            ratingService.removeTrackRating(userId, trackId);
        else
            trackService.saveRating(userId, trackId, rating);

        trackService.updateTrackAverage(trackId);

        return "redirect:/track/".concat(String.valueOf(trackId));
    }

}
