package com.musicsite.controller;

import com.musicsite.entity.Album;
import com.musicsite.entity.Performer;
import com.musicsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/album")
public class AlbumController {

    private UserService userService;
    private AlbumService albumService;
    private RatingService ratingService;

    @Autowired
    public AlbumController(UserService userService,
                           AlbumService albumService,
                           RatingService ratingService) {
        this.userService = userService;
        this.albumService = albumService;
        this.ratingService = ratingService;
    }


    @GetMapping("/{id}")
    public String showForm(@PathVariable String id, Model model, HttpSession session) {
        Album album = albumService.getAlbum(Long.parseLong(id));
        if (album == null || album.isProposition())
            return "main/blank";

        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId != null)
            model.addAttribute("userAlbumRating", userService.getAlbumUserRating(userId, album));


        model.addAttribute("album", album);

        return "main/album";
    }

    @GetMapping("/{albumId}/setRate/{rating}")
    public String ratePerformer(@PathVariable Long albumId, @PathVariable int rating, HttpSession session) {

        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null)
            return "redirect:/login";

        if (userService.getAlbumUserRating(userId, albumService.getAlbum(albumId)) == (rating))
            ratingService.removePerformerRating(userId, albumId);
        else
            albumService.saveRating(userId, albumId, rating);

        albumService.updateAlbumAverage(albumId);

        return "redirect:/album/".concat(String.valueOf(albumId));
    }

}
