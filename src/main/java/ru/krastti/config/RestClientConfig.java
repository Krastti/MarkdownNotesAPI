package ru.krastti.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${language-tool.client.base-url}")
    private String baseUrl;

    @Bean
    public RestClient languageToolServiceRestClient() {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
