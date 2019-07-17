package com.musicsite.controller;

import com.musicsite.entity.User;
import com.musicsite.repository.UserRepository;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/usr")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/settings")
    public String editUser(Model model, HttpSession session) {
        User user = userService.getUserById((Long) session.getAttribute("loggedUserId"));
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/settings")
    public String updateUser(@Valid User newUser, BindingResult result, HttpSession session) {
        User originUser = userService.getUserById((Long) session.getAttribute("loggedUserId"));

        if (!BCrypt.checkpw(newUser.getPassword(), originUser.getPassword())) {
            result.addError(new FieldError("user", "email", "Incorrect password!"));
            return "user/edit";
        }

        if (result.hasErrors())
            return "user/edit";


        if (isEmailNonUnique(newUser, originUser)) {
            result.addError(new FieldError("user", "email", "User with this email already exists"));
            return "user/edit";
        }


        userService.updateUser(originUser, newUser);
        session.setAttribute("name", originUser.getFirstName());



        return "redirect:/";
    }

    private boolean isEmailNonUnique(@Valid User newUser, User originUser) {
        return userService.getUserByEmail(newUser.getEmail()) != null && !userService.getUserByEmail(newUser.getEmail()).getId().equals(originUser.getId());
    }
}
