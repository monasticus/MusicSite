package com.musicsite.album;

import com.musicsite.category.CategoryService;
import com.musicsite.comment.Comment;
import com.musicsite.comment.CommentService;
import com.musicsite.rating.RatingService;
import com.musicsite.track.Track;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/album")
public class AlbumController {

    private UserService userService;
    private TrackService trackService;
    private AlbumService albumService;
    private RatingService ratingService;
    private CommentService commentService;
    private CategoryService categoryService;

    @Autowired
    public AlbumController(UserService userService,
                           AlbumService albumService,
                           TrackService trackService,
                           RatingService ratingService,
                           CommentService commentService,
                           CategoryService categoryService) {
        this.userService = userService;
        this.albumService = albumService;
        this.trackService = trackService;
        this.ratingService = ratingService;
        this.commentService = commentService;
        this.categoryService = categoryService;

    }


    @GetMapping("/{id}")
    public String showForm(@PathVariable String id, Model model, HttpSession session) {
        Album album = albumService.getAlbum(Long.parseLong(id));
        if (album == null || album.isProposition())
            return "main/blank";

        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId != null) {
            model.addAttribute("userAlbumRating", userService.getAlbumUserRating(userId, album));
            model.addAttribute("comment", new Comment().setUser(userService.getUserById(userId)).setAlbum(album));
        }

        model.addAttribute("album", album);
        model.addAttribute("tracksNoProposition", trackService.getTracksByAlbumAndPropositionsOrderByYearSaute(album, false));
        return "main/album";
    }

    @PostMapping("/{albumId}")
    public String comment(@Valid Comment comment, BindingResult result, HttpSession session, Model model, @PathVariable Long albumId) {
        if (result.hasErrors()) {
            Album album = albumService.getAlbum(albumId);
            Long userId = (Long) session.getAttribute("loggedUserId");
            model.addAttribute("userAlbumRating", userService.getAlbumUserRating(userId, album));
            model.addAttribute("album", album);

            return "main/album";
        }

        comment.setAlbum(albumService.getAlbum(albumId));
        commentService.save(comment);
        return "redirect:/album/".concat(String.valueOf(albumId));
    }

    @GetMapping("/{albumId}/setRate/{rating}")
    public String ratePerformer(@PathVariable Long albumId, @PathVariable int rating, HttpSession session) {

        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null)
            return "redirect:/login";

        if (userService.getAlbumUserRating(userId, albumService.getAlbum(albumId)) == rating)
            ratingService.removeAlbumRating(userId, albumId);
        else
            albumService.saveRating(userId, albumId, rating);

        albumService.updateAlbumAverage(albumId);

        return "redirect:/album/".concat(String.valueOf(albumId));
    }

    @GetMapping("/add/tracks/{albumId}")
    public String displayAlbumTracksForm (@PathVariable Long albumId, Model model) {
        Album album = albumService.getAlbum(albumId);
        if (album == null)
            return "main/blank";

        model.addAttribute("albumName", album.getName());
        return "add/tracks";
    }

    @PostMapping("/add/tracks/{albumId}")
    public String addTracksToAlbum(@PathVariable Long albumId, Model model, HttpServletRequest request) {
        String[] names = request.getParameterValues("trackName");
        model.addAttribute("albumName", albumService.getAlbum(albumId).getName());
        if (names == null){
            int counter = Integer.valueOf(request.getParameter("counter"));
            model.addAttribute("counter", counter);
            model.addAttribute("categories", categoryService.getCategories());
            return "add/tracks";
        }

        List<String> trackNames = Arrays.asList(names);
        List<String> categories = Arrays.asList(request.getParameterValues("category"));
        for (int i = 0; i < trackNames.size(); i++) {

            Album album = albumService.getAlbum(albumId);
            Track track = new Track();
            track.setName(trackNames.get(i));
            track.setPerformer(album.getPerformer());
            track.setAlbum(album);
            track.setYearOfPublication(album.getYearOfPublication());
            track.setOrdinalNum(i+1);
            track.setCategory(categoryService.getCategoryByName(categories.get(i)));
            trackService.save(track);
        }


        return "redirect:/album/".concat(String.valueOf(albumId));
    }

}