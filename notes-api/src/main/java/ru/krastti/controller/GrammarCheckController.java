package ru.krastti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.krastti.entity.dto.NoteDTO;
import ru.krastti.entity.dto.result.GrammarCheckResult;
import ru.krastti.service.GrammarCheckerService;
import ru.krastti.service.NoteService;

@RestController
@RequestMapping("/api")
public class GrammarCheckController {

    private final GrammarCheckerService grammarCheckerService;
    private final NoteService noteService;

    @Autowired
    public GrammarCheckController(GrammarCheckerService grammarCheckerService, NoteService noteService) {
        this.grammarCheckerService = grammarCheckerService;
        this.noteService = noteService;
    }

    @PostMapping("/notes/{id}/grammar-check")
    public GrammarCheckResult checkGrammar(
            @PathVariable int id,
            @RequestParam(defaultValue = "ru-RU") String language
    ) {
        NoteDTO note = noteService.findById(id);
        return grammarCheckerService.checkNote(note, language);
    }
}
