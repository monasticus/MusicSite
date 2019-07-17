package com.musicsite.controller;

import com.musicsite.entity.Track;
import com.musicsite.entity.User;
import com.musicsite.service.TrackService;
import com.musicsite.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private TrackService trackService;

    @Autowired
    public HomeController(UserService userService, TrackService trackService) {
        this.userService = userService;
        this.trackService = trackService;
    }

    @RequestMapping("/")
    public String displayHomePage(Model model) {
        List<Track> tracks = trackService.getRandomTracks(3);
        model.addAttribute("firstTrack", trackService.getYoutubeURL(tracks.get(0).getId()));
        model.addAttribute("secondTrack", trackService.getYoutubeURL(tracks.get(1).getId()));
        model.addAttribute("thirdTrack", trackService.getYoutubeURL(tracks.get(2).getId()));
        return "main/home";
    }

    @GetMapping("/login")
    public String displayLoginForm() {
        return "main/login";
    }

    @PostMapping("/login")
    public String logingIn(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {
        boolean success = true;

        if (isNotCorrect(email, password)) {
            success = false;
        }

        User user = null;
        if (success) {
            user = userService.getUserByEmail(email);
            if (user == null)
                success = false;
            else if (!BCrypt.checkpw(password, user.getPassword()))
                success = false;
        }

        if (!success) {
            model.addAttribute("correct", success);
            return "main/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loggedUserId", user.getId());
        session.setAttribute("name", user.getFirstName());
        session.setAttribute("capo", user.isAdmin());
        return "redirect:/";
    }

    private boolean isNotCorrect(String email, String password) {
        return email == null || email.trim().equals("") || password == null || password.trim().equals("");
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "main/register";
    }

    @PostMapping("/register")
    public String createUser(@Valid User user, BindingResult result,
                             @RequestParam String confirmPassword) {

        if (result.hasErrors())
            return "main/register";


        if (confirmPassword.length() < 1) {
            result.addError(new FieldError("user", "password", "Confirm password!"));
            return "main/register";
        }


        if (!confirmPassword.equals(user.getPassword())) {
            result.addError(new FieldError("user", "password", "Passwords do not match!"));
            return "main/register";
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 30){
            result.addError(new FieldError("user", "password", "The password must contain between 8 and 30 characters!"));
            return "main/register";
        }

        if (userService.getUserByEmail(user.getEmail()) != null) {
            result.addError(new FieldError("user", "email", "User already exists"));
            return "main/register";
        }

        userService.save(user);

        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
