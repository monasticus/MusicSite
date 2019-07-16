package com.musicsite.controller;


import com.musicsite.entity.*;
import com.musicsite.repository.*;
import com.musicsite.service.PerformerService;
import com.musicsite.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/performer")
public class PerformerController {
    private PerformerService performerService;
    private UserService userService;

    @Autowired
    public PerformerController(PerformerService performerService,
                               UserService userService) {
        this.performerService = performerService;
        this.userService = userService;
    }

//    @ModelAttribute("performers")
//    public List<Performer> getPerformers() {
//        return performerService.getPerformers();
//    }

    @GetMapping("/{id}")
    public String showForm(@PathVariable String id, Model model, HttpSession session) {
        Performer performer = performerService.getPerformer(Long.parseLong(id));
        if (performer == null)
            return "main/blank";

        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId != null)
            model.addAttribute("userPerformerRating", userService.getPerformerUserRating(userId, performer));


        performerService.orderData(performer);
        model.addAttribute("performer", performer);

        return "main/performer";
    }

    @GetMapping("/{performerId}/setRate/{rating}")
    public String ratePerformer(@PathVariable Long performerId, @PathVariable int rating, HttpSession session){

        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null)
            return "redirect:/login";

        performerService.saveRating(userId, performerId, rating);
        performerService.updatePerformerAverage(performerId);

        return "redirect:/performer/".concat(String.valueOf(performerId));
    }

}
