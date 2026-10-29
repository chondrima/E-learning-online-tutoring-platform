package com.chondrimaishrat.elearning.web;

import com.chondrimaishrat.elearning.registration.RegistrationForm;
import com.chondrimaishrat.elearning.registration.RegistrationService;
import com.chondrimaishrat.elearning.user.UserRole;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid RegistrationForm registrationForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            UserRole role = registrationService.register(registrationForm);

            return "redirect:/registration-success?role=" + role.name();
        } catch (IllegalArgumentException exception) {
            bindingResult.reject("registration", exception.getMessage());
            return "register";
        }
    }

    @GetMapping("/registration-success")
    public String registrationSuccess(
            @RequestParam String role,
            Model model
    ) {
        model.addAttribute("role", role);
        return "registration-success";
    }
}