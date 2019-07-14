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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/usr")
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/settings")
    public String editUser(Model model, HttpSession session) {
        session.setAttribute("chances", 3);
        User user = userRepository.findOne((Long) session.getAttribute("id"));
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/settings")
    public String updateUser(@Valid User user, BindingResult result, HttpSession session) {
        User originUser = userRepository.findOne((Long) session.getAttribute("id"));

        if (!BCrypt.checkpw(user.getTempPassword(), originUser.getPassword())) {
            session.setAttribute("chances", ((int) session.getAttribute("chances") - 1));
            if ((int) session.getAttribute("chances") == 0)
                return "redirect:/logout";

            result.addError(new FieldError("user", "email", "Incorrect password!"));
            return "user/edit";
        }

        if (result.hasErrors())
            return "user/edit";


        if (userRepository.getUserByEmailIgnoreCase(user.getEmail()) != null && !userRepository.getUserByEmailIgnoreCase(user.getEmail()).getId().equals(originUser.getId())) {

            result.addError(new FieldError("user", "email", "User with this email already exists"));
            return "user/edit";
        }

        originUser.setEmail(user.getEmail());
        originUser.setFirstName(user.getFirstName());
        originUser.setUsername(user.getUsername());

        session.setAttribute("name", user.getFirstName());

        userRepository.save(originUser);

        return "redirect:/";
    }
}
