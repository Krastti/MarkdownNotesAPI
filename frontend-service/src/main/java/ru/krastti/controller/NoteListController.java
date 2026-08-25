package ru.krastti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.krastti.client.NotesApiClient;

@Controller
public class NoteListController {

    private final NotesApiClient notesApiClient;

    public NoteListController(NotesApiClient notesApiClient) {
        this.notesApiClient = notesApiClient;
    }

    @GetMapping("/")
    public String redirectToNotes() {
        return "redirect:/notes";
    }

    @GetMapping("/notes")
    public String listNotes(Model model) {
        model.addAttribute("notes", notesApiClient.findAll());
        return "notes/list";
    }
}