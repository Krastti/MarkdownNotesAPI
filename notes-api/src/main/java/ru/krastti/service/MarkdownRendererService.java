package ru.krastti.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import ru.krastti.client.MarkdownRendererClient;
import ru.krastti.entity.dto.NoteDTO;
import ru.krastti.entity.dto.responce.MarkdownRenderResponse;
import ru.krastti.exception.MarkdownRenderException;

@Service
public class MarkdownRendererService {

    private final MarkdownRendererClient markdownRendererClient;

    public MarkdownRendererService(MarkdownRendererClient markdownRendererClient) {
        this.markdownRendererClient = markdownRendererClient;
    }

    public String renderNote(NoteDTO note) {
        try {
            MarkdownRenderResponse response = markdownRendererClient.render(note.text());
            return response.html();
        } catch (RestClientException e) {
            throw new MarkdownRenderException("Markdown renderer service is unavailable.", e);
        }
    }
}