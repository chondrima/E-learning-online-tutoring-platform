package com.chondrimaishrat.elearning.web;

import com.chondrimaishrat.elearning.auth.AuthService;
import com.chondrimaishrat.elearning.auth.LoginForm;
import com.chondrimaishrat.elearning.auth.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/";
        }

        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @Valid LoginForm loginForm,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            SessionUser user = authService.authenticate(loginForm);
            session.setAttribute("currentUser", user);
            return "redirect:/";
        } catch (IllegalArgumentException exception) {
            bindingResult.reject("login", exception.getMessage());
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
