package com.musicsite.controller;

import com.musicsite.entity.Album;
import com.musicsite.entity.Performer;
import com.musicsite.service.AlbumService;
import com.musicsite.service.PerformerService;
import com.musicsite.service.TrackService;
import com.musicsite.service.UserService;
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

    @Autowired
    public AlbumController(UserService userService,
                           AlbumService albumService) {
        this.userService = userService;
        this.albumService = albumService;
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

//        if (userId == null)
//            return "redirect:/login";

        albumService.saveRating(userId, albumId, rating);
        albumService.updateAlbumAverage(albumId);

        return "redirect:/album/".concat(String.valueOf(albumId));
    }

}
