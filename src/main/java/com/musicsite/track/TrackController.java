package com.musicsite.track;

import com.musicsite.comment.Comment;
import com.musicsite.comment.CommentService;
import com.musicsite.favorite.FavoriteService;
import com.musicsite.rating.RatingService;
import com.musicsite.recommendation.RecommendationService;
import com.musicsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/track")
public class TrackController {

    private UserService userService;
    private TrackService trackService;
    private RatingService ratingService;
    private CommentService commentService;
    private RecommendationService recommendationService;
    private FavoriteService favoriteService;

    @Autowired
    public TrackController(UserService userService,
                           TrackService trackService,
                           RatingService ratingService,
                           CommentService commentService,
                           RecommendationService recommendationService,
                           FavoriteService favoriteService) {
        this.userService = userService;
        this.trackService = trackService;
        this.ratingService = ratingService;
        this.commentService = commentService;
        this.recommendationService = recommendationService;
        this.favoriteService = favoriteService;
    }


    @GetMapping("/{id}")
    public String showForm(@PathVariable Long id, Model model, HttpSession session) {
        Track track = trackService.getTrack(id);
        if (track == null || track.isProposition())
            return "main/blank";

        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId != null) {
            model.addAttribute("userTrackRating", userService.getTrackUserRating(userId, track));
            model.addAttribute("recommendation", recommendationService.getEnsUserRecommendation(userId, trackService.getTrack(id)));
            model.addAttribute("favorite", favoriteService.getEnsUserFavorite(userId, trackService.getTrack(id)));
            model.addAttribute("comment", new Comment().setUser(userService.getUserById(userId)).setTrack(track));
        }

        String hyperlink = trackService.getYoutubeURL(id);
        model.addAttribute("trackHyperlink", hyperlink);
        model.addAttribute("track", track);
        model.addAttribute("recommendationCounter", recommendationService.countRecommend(track));
        model.addAttribute("ratingCounter", ratingService.countRatings(track));

        return "main/track";
    }

    @PostMapping("/{trackId}")
    public String comment(@Valid Comment comment, BindingResult result, HttpSession session, Model model, @PathVariable Long trackId) {
        if (result.hasErrors()) {
            Track track = trackService.getTrack(trackId);
            Long userId = (Long) session.getAttribute("loggedUserId");
            model.addAttribute("userTrackRating", userService.getTrackUserRating(userId, track));
            String hyperlink = trackService.getYoutubeURL(trackId);
            model.addAttribute("trackHyperlink", hyperlink);
            model.addAttribute("track", track);

            return "main/track";
        }

        comment.setTrack(trackService.getTrack(trackId));
        commentService.save(comment);
        return "redirect:/track/".concat(String.valueOf(trackId));
    }

//    @GetMapping("/{trackId}/setRate/{rating}")
//    public String ratePerformer(@PathVariable Long trackId, @PathVariable int rating, HttpSession session) {
//
//        Long userId = (Long) session.getAttribute("loggedUserId");
//
//        if (userId == null)
//            return "redirect:/login";
//
//        Track track = trackService.getTrack(trackId);
//
//        if (userService.getTrackUserRating(userId, track) == (rating))
//            ratingService.removeEnsRating(userId, track);
//        else
//            ratingService.setRating(userId, track, rating);
//
//        trackService.updateTrackAverage(track);
//
//        return "redirect:/track/".concat(String.valueOf(trackId));
//    }

}
