package com.ui.controller;

import com.ui.model.Student;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/studenti")
    public String studenti(Model model, HttpSession session) {
        String token = (String) session.getAttribute("jwt");
        if (token == null) {
            return "redirect:/login";
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<Student[]> response = restTemplate.exchange(
                "http://localhost:8080/student-service/students",
                HttpMethod.GET,
                entity,
                Student[].class
            );

            List<Student> studenti = Arrays.asList(response.getBody());
            model.addAttribute("studenti", studenti);

        } catch (Exception e) {
            model.addAttribute("error", "Eroare la conectarea cu serverul student-service.");
        }

        return "studenti";
    }
}