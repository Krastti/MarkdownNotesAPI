package ru.krastti.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${notes-api.client.base-url}")
    private String notesApiBaseUrl;

    @Bean
    public RestClient notesApiRestClient() {
        return RestClient.builder()
                .baseUrl(notesApiBaseUrl)
                .build();
    }
}