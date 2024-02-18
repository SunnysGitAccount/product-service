package com.dev.sunny.commons;

import com.dev.sunny.dtos.UserResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {
    private static final String AUTH_URL = "http://localhost:8180/api/users";
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public AuthenticationCommons(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
    }

    public boolean validateToken(String token) {
        final String VALIDATE_TOKEN_RESOURCE = "/validate";
        if (token == null) return false;
        headers.set("token", token);

        ResponseEntity<UserResponseDto> response = restTemplate.postForEntity(AUTH_URL + VALIDATE_TOKEN_RESOURCE,
                new HttpEntity<>(headers),
                UserResponseDto.class);
        return response.hasBody();
    }
}
