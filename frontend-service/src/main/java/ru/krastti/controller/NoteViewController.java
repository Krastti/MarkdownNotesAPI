package ru.krastti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.krastti.client.NotesApiClient;
import ru.krastti.entity.dto.NoteDTO;

@Controller
public class NoteViewController {

    private final NotesApiClient notesApiClient;

    public NoteViewController(NotesApiClient notesApiClient) {
        this.notesApiClient = notesApiClient;
    }

    @GetMapping("/notes/{id}")
    public String viewNote(@PathVariable int id, Model model) {
        NoteDTO note = notesApiClient.findById(id);
        String renderedHtml = notesApiClient.render(id);

        model.addAttribute("note", note);
        model.addAttribute("renderedHtml", renderedHtml);

        return "notes/view";
    }
}