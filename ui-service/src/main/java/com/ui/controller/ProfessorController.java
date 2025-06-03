package com.ui.controller;

import com.ui.model.Professor;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProfessorController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/profesori")
    public String profesori(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/login";
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<Professor[]> response = restTemplate.exchange(
                "http://localhost:8080/professor-service/professors",
                HttpMethod.GET,
                entity,
                Professor[].class
            );

            List<Professor> profesori = Arrays.asList(response.getBody());
            model.addAttribute("profesori", profesori);

        } catch (Exception e) {
            model.addAttribute("error", "Eroare la preluarea profesorilor.");
        }

        return "profesori";
    }
}