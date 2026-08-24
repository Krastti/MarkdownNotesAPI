package ru.krastti.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import ru.krastti.entity.dto.NoteDTO;
import ru.krastti.entity.dto.responce.LanguageToolResponse;

@Component
public class GrammarCheckerClient {
    private final RestClient restClient;

    @Autowired
    public GrammarCheckerClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public LanguageToolResponse grammarCheck(String text, String language) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>(); // TODO Добавить сериализацию DTO класса
        formData.add("text", text);
        formData.add("language", language);

        return restClient.post()
                .uri("/v2/check")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(LanguageToolResponse.class);
    }
}
