package com.musicsite.performer;


import com.musicsite.album.AlbumService;
import com.musicsite.comment.Comment;
import com.musicsite.comment.CommentService;
import com.musicsite.favorite.FavoriteService;
import com.musicsite.rating.RatingService;
import com.musicsite.recommendation.RecommendationService;
import com.musicsite.track.TrackService;
import com.musicsite.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/performer")
public class PerformerController {
    private PerformerService performerService;
    private UserService userService;
    private AlbumService albumService;
    private TrackService trackService;
    private CommentService commentService;
    private RatingService ratingService;
    private RecommendationService recommendationService;
    private FavoriteService favoriteService;

    @Autowired
    public PerformerController(PerformerService performerService,
                               UserService userService,
                               AlbumService albumService,
                               TrackService trackService,
                               CommentService commentService,
                               RatingService ratingService,
                               RecommendationService recommendationService,
                               FavoriteService favoriteService) {
        this.performerService = performerService;
        this.userService = userService;
        this.albumService = albumService;
        this.trackService = trackService;
        this.commentService = commentService;
        this.ratingService = ratingService;
        this.recommendationService = recommendationService;
        this.favoriteService = favoriteService;
    }


    @GetMapping("/{id}")
    public String showForm(@PathVariable Long id, Model model, HttpSession session) {
        Performer performer = performerService.getPerformer(id);
        if (performer == null || performer.isProposition())
            return "main/blank";

        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId != null) {
            model.addAttribute("userPerformerRating", userService.getPerformerUserRating(userId, performer));
            model.addAttribute("recommendation", recommendationService.getEnsUserRecommendation(userId, performerService.getPerformer(id)));
            model.addAttribute("favorite", favoriteService.getEnsUserFavorite(userId, performerService.getPerformer(id)));
            model.addAttribute("comment", new Comment().setUser(userService.getUserById(userId)).setPerformer(performer));
        }

        performerService.orderData(performer);
        model.addAttribute("performer", performer);
        model.addAttribute("performerAlbums", albumService.getAlbumsByPerformerAndPropositionOrderByYear(performer, false));
        model.addAttribute("performerTracks", trackService.getTracksByPerformerAndPropositionsOrderByYear(performer, false));
        model.addAttribute("recommendationCounter", recommendationService.countRecommend(performer));
        model.addAttribute("ratingCounter", ratingService.countRatings(performer));

        return "main/performer";
    }

    @PostMapping("/{performerId}")
    public String comment(@Valid Comment comment, BindingResult result, HttpSession session, Model model, @PathVariable Long performerId) {
        if (result.hasErrors()) {
            Performer performer = performerService.getPerformer(performerId);
            performerService.orderData(performer);
            Long userId = (Long) session.getAttribute("loggedUserId");
            model.addAttribute("userPerformerRating", userService.getPerformerUserRating(userId, performer));
            model.addAttribute("performer", performer);
            model.addAttribute("performerAlbums", albumService.getAlbumsByPerformerAndPropositionOrderByYear(performer, false));
            model.addAttribute("performerTracks", trackService.getTracksByPerformerAndPropositionsOrderByYear(performer, false));

            return "main/performer";
        }

        comment.setPerformer(performerService.getPerformer(performerId));
        commentService.save(comment);
        return "redirect:/performer/".concat(String.valueOf(performerId));
    }

}

