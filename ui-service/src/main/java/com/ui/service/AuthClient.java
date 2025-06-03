package com.ui.service;

import com.ui.model.LoginRequest;
import com.ui.model.LoginResponse;
import com.ui.model.RegisterRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthClient {

    @Value("${gateway.url}")
    private String gatewayUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public LoginResponse login(LoginRequest request) {
        String url = gatewayUrl + "/auth-service/auth/login";
        return restTemplate.postForObject(url, request, LoginResponse.class);
    }

    public ResponseEntity<Void> register(RegisterRequest request) {
        String url = gatewayUrl + "/auth-service/auth/register";
        try {
            return restTemplate.postForEntity(url, request, Void.class);
        } catch (HttpClientErrorException e) {
            System.out.println("‼️ Eroare 4xx de la auth-service: " + e.getResponseBodyAsString());
            throw new RuntimeException(e.getResponseBodyAsString());  // trimitem motivul real către controller
        } catch (Exception e) {
            throw new RuntimeException("Înregistrare eșuată", e);
        }
    }

}
