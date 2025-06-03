package com.ui.controller;

import com.ui.model.LoginRequest;
import com.ui.service.AuthClient;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AuthClient authClient;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute LoginRequest loginRequest, Model model, HttpSession session) {
        try {
            var response = authClient.login(loginRequest);
            session.setAttribute("jwt", response.getToken());
            return "redirect:/studenti"; // sau alta rută validă
        } catch (Exception e) {
            model.addAttribute("error", "Autentificare eșuată: " + e.getMessage());
            model.addAttribute("loginRequest", new LoginRequest());
            return "login";
        }
    }
}
