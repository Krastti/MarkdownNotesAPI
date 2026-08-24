package ru.krastti.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krastti.entity.dto.NoteDTO;
import ru.krastti.service.MarkdownRendererService;
import ru.krastti.service.NoteService;

@RestController
@RequestMapping("/api")
public class MarkdownController {

    private final MarkdownRendererService markdownRendererService;
    private final NoteService noteService;

    public MarkdownController(
            MarkdownRendererService markdownRendererService,
            NoteService noteService) {
        this.markdownRendererService = markdownRendererService;
        this.noteService = noteService;
    }

    @PostMapping(
            value = "/notes/{id}/render",
            produces = MediaType.TEXT_HTML_VALUE
    )
    public String renderNote(@PathVariable int id) {
        NoteDTO note = noteService.findById(id);
        return markdownRendererService.renderNote(note);
    }
}