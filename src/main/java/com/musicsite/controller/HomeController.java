package com.musicsite.controller;

import com.musicsite.entity.User;
import com.musicsite.repository.UserRepository;
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

@Controller
public class HomeController {

    UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String displayHomePage() {
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
            user = userRepository.getUserByEmailIgnoreCase(email);
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

    private boolean isNotCorrect(@RequestParam String email, @RequestParam String password) {
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

        if (!user.getTempPassword().equals(confirmPassword)) {
            result.addError(new FieldError("user", "password", "Passwords do not match!"));
            return "main/register";
        }

        if (userRepository.getUserByEmailIgnoreCase(user.getEmail()) != null) {
            result.addError(new FieldError("user", "email", "User already exists"));
            return "main/register";
        }

        userRepository.save(user);

        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
