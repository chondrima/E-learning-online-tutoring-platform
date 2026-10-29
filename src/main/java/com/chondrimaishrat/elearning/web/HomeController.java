package com.chondrimaishrat.elearning.web;

import com.chondrimaishrat.elearning.auth.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute(
                "platformName",
                "E-Learning & Online Tutoring Platform"
        );
        model.addAttribute("currentUser", session.getAttribute("currentUser"));

        return "home";
    }
}
