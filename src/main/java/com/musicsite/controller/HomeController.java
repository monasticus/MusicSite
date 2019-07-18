package com.musicsite.controller;

import com.musicsite.entity.Track;
import com.musicsite.entity.User;
import com.musicsite.service.EmailService;
import com.musicsite.service.TrackService;
import com.musicsite.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private TrackService trackService;
    private EmailService emailService;


    @Autowired
    public HomeController(UserService userService,
                          TrackService trackService,
                          EmailService emailService) {
        this.userService = userService;
        this.trackService = trackService;
        this.emailService = emailService;
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

        if (!user.isConfirmed()){
            model.addAttribute("justConfirmed", false);
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
        sendConfirmingEmail(user);

        return "redirect:login";
    }

    private void sendConfirmingEmail(@Valid User user) {
        Long userId = userService.getUserByUsername(user.getUsername()).getId();
        try {
            emailService.sendHTMLEmail(user.getEmail(), "Confirm registration", userId, BCrypt.hashpw(user.getUsername(), BCrypt.gensalt()));
        } catch (MessagingException e) {
            emailService.sendSimpleMessage(user.getEmail(), "Registration failed", "If you see this message it means that something goes wrong during the registration. Try again.");
            userService.removeUser(userId);
        }
    }

    @RequestMapping("/user-confirm/{id}")
    public String confirmUser(@PathVariable Long id, @RequestParam String x, Model model) {
        User user = userService.getUserById(id);
        if(user.isConfirmed())
            return "main/blank";

        try {
            if (!BCrypt.checkpw(user.getUsername(), x))
                return "main/blank";
        } catch (IllegalArgumentException e) {
            return "main/blank";
        }

        userService.confirm(id);
        model.addAttribute("justConfirmed", true);
        return "main/login";
    }


    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
