package com.musicsite.controller;


import com.musicsite.entity.*;
import com.musicsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public PerformerController(PerformerService performerService,
                               UserService userService,
                               AlbumService albumService,
                               TrackService trackService,
                               CommentService commentService,
                               RatingService ratingService) {
        this.performerService = performerService;
        this.userService = userService;
        this.albumService = albumService;
        this.trackService = trackService;
        this.commentService = commentService;
        this.ratingService = ratingService;
    }


    @GetMapping("/{id}")
    public String showForm(@PathVariable Long id, Model model, HttpSession session) {
        Performer performer = performerService.getPerformerById(id);
        if (performer == null || performer.isProposition())
            return "main/blank";

        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId != null) {
            model.addAttribute("userPerformerRating", userService.getPerformerUserRating(userId, performer));
            model.addAttribute("comment", new Comment().setUser(userService.getUserById(userId)).setPerformer(performer));
        }

        performerService.orderData(performer);
        model.addAttribute("performer", performer);
        model.addAttribute("performerAlbums", albumService.getAlbumsByPerformerAndPropositionOrderByYear(performer, false));
        model.addAttribute("performerTracks", trackService.getTracksByPerformerAndPropositionsOrderByYear(performer, false));

        return "main/performer";
    }

    @PostMapping("/{performerId}")
    public String comment(@Valid Comment comment, BindingResult result, HttpSession session, Model model, @PathVariable Long performerId) {
        if (result.hasErrors()) {
            Performer performer = performerService.getPerformerById(performerId);
            Long userId = (Long) session.getAttribute("loggedUserId");
            model.addAttribute("userPerformerRating", userService.getPerformerUserRating(userId, performer));
            model.addAttribute("comment", new Comment().setUser(userService.getUserById(userId)).setPerformer(performer));
            performerService.orderData(performer);
            model.addAttribute("performer", performer);
            model.addAttribute("performerAlbums", albumService.getAlbumsByPerformerAndPropositionOrderByYear(performer, false));
            model.addAttribute("performerTracks", trackService.getTracksByPerformerAndPropositionsOrderByYear(performer, false));

            return "main/performer";
        }

        comment.setPerformer(performerService.getPerformerById(performerId));
        commentService.save(comment);
        return "redirect:/performer/".concat(String.valueOf(performerId));
    }

    @GetMapping("/{performerId}/setRate/{rating}")
    public String ratePerformer(@PathVariable Long performerId, @PathVariable int rating, HttpSession session) {

        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null)
            return "redirect:/login";

        if (userService.getPerformerUserRating(userId, performerService.getPerformerById(performerId)) == (rating))
            ratingService.removePerformerRating(userId, performerId);
        else
            performerService.saveRating(userId, performerId, rating);

        performerService.updatePerformerAverage(performerId);

        return "redirect:/performer/".concat(String.valueOf(performerId));
    }


}

