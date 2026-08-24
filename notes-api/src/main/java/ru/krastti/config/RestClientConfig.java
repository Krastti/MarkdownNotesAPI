package ru.krastti.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean("languageToolServiceRestClient")
    public RestClient languageToolServiceRestClient(
            @Value("${language-tool.client.base-url}") String languageToolBaseUrl) {
        return RestClient.builder()
                .baseUrl(languageToolBaseUrl)
                .build();
    }

    @Bean("markdownRendererServiceRestClient")
    public RestClient markdownRendererServiceRestClient(
            @Value("${markdown-renderer.client.base-url}") String markdownRendererBaseUrl) {
        return RestClient.builder()
                .baseUrl(markdownRendererBaseUrl)
                .build();
    }
}
