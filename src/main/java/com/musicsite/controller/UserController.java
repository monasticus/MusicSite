package com.musicsite.controller;

import com.musicsite.entity.Comment;
import com.musicsite.entity.Ens;
import com.musicsite.entity.User;
import com.musicsite.repository.UserRepository;
import com.musicsite.service.CommentService;
import com.musicsite.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/usr")
public class UserController {

    private UserService userService;
    private CommentService commentService;

    @Autowired
    public UserController(UserService userService, CommentService commentService) {
        this.commentService = commentService;
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

    @RequestMapping("/comment-remove/{commentId}")
    public String removeComment(@PathVariable Long commentId, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedUserId");
        Comment comment = commentService.getComment(commentId);
        if (!comment.getUser().getId().equals(userId))
            return "main/error";

        String direct = new String();
        Long id = 0L;
        if (comment.getAlbum() != null){
            id = comment.getAlbum().getId();
            direct = "album";
        } else if (comment.getPerformer() != null){
            id = comment.getPerformer().getId();
            direct = "performer";
        } else if (comment.getTrack() != null){
            id = comment.getTrack().getId();
            direct = "track";
        }

        commentService.remove(commentId);
        return "redirect:/".concat(direct).concat("/").concat(String.valueOf(id));
    }
}
