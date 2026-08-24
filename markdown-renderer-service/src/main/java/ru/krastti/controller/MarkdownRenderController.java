package ru.krastti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.krastti.entity.dto.request.MarkdownRenderRequest;
import ru.krastti.entity.dto.response.MarkdownRenderResponse;
import ru.krastti.service.MarkdownRenderService;

@RestController
public class MarkdownRenderController {

    private final MarkdownRenderService markdownRenderService;

    @Autowired
    public MarkdownRenderController(MarkdownRenderService markdownRenderService) {
        this.markdownRenderService = markdownRenderService;
    }

    @PostMapping("/render")
    public MarkdownRenderResponse render(@RequestBody MarkdownRenderRequest request) {
        String html = markdownRenderService.renderToHtml(request.markdown());
        return new MarkdownRenderResponse(html);
    }
}
