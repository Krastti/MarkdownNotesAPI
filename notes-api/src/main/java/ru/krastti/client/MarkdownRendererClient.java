package ru.krastti.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.krastti.entity.dto.request.MarkdownRenderRequest;
import ru.krastti.entity.dto.responce.MarkdownRenderResponse;

@Component
public class MarkdownRendererClient {

    private final RestClient restClient;

    @Autowired
    public MarkdownRendererClient(
            @Qualifier("markdownRendererServiceRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public MarkdownRenderResponse render(String markdown) {
        MarkdownRenderRequest request = new MarkdownRenderRequest(markdown);

        return restClient.post()
                .uri("/render")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(MarkdownRenderResponse.class);
    }
}
