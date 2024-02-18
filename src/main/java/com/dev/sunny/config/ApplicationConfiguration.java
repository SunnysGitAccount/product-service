package com.dev.sunny.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        return new HttpHeaders();
    }
}
