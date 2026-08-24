package ru.krastti.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.krastti.entity.dto.NoteDTO;
import ru.krastti.entity.dto.request.LanguageToolRequest;
import ru.krastti.entity.dto.responce.LanguageToolResponse;

@Component
public class GrammarCheckerClient {
    private final RestClient restClient;

    @Autowired
    public GrammarCheckerClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public LanguageToolResponse grammarCheck(NoteDTO body) {
        return restClient.post()
                .uri("/v2/check")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(LanguageToolRequest.class)
                .retrieve()
                .body(LanguageToolResponse.class);
    }
}
