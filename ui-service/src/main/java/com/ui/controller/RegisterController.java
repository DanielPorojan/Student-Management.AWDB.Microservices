package com.ui.controller;

import com.ui.model.RegisterRequest;
import com.ui.service.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private AuthClient authClient;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute RegisterRequest registerRequest, Model model) {
        try {
            ResponseEntity<Void> response = authClient.register(registerRequest);
            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("success", "Înregistrare reușită!");
            } else {
                model.addAttribute("error", "Înregistrare eșuată!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Eroare la înregistrare: " + e.getMessage());
        }
        return "register";
    }

}
