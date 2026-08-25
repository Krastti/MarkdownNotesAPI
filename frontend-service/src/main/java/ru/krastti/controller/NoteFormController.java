package ru.krastti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.krastti.client.NotesApiClient;
import ru.krastti.entity.dto.NoteDTO;
import ru.krastti.entity.dto.request.CreateNoteRequest;
import ru.krastti.entity.dto.request.UpdateNoteRequest;

@Controller
public class NoteFormController {

    private final NotesApiClient notesApiClient;

    public NoteFormController(NotesApiClient notesApiClient) {
        this.notesApiClient = notesApiClient;
    }

    @GetMapping("/notes/new")
    public String showCreateForm(Model model) {
        model.addAttribute("request", new CreateNoteRequest("", ""));
        return "notes/create";
    }

    @PostMapping("/notes")
    public String createNote(@ModelAttribute CreateNoteRequest request) {
        NoteDTO createdNote = notesApiClient.create(request);
        return "redirect:/notes/" + createdNote.id();
    }

    @GetMapping("/notes/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        NoteDTO note = notesApiClient.findById(id);
        UpdateNoteRequest request = new UpdateNoteRequest(note.title(), note.text());

        model.addAttribute("note", note);
        model.addAttribute("request", request);
        return "notes/edit";
    }

    @PostMapping("/notes/{id}/edit")
    public String updateNote(@PathVariable int id, @ModelAttribute UpdateNoteRequest request) {
        notesApiClient.update(id, request);
        return "redirect:/notes/" + id;
    }

    @PostMapping("/notes/{id}/delete")
    public String deleteNote(@PathVariable int id) {
        notesApiClient.delete(id);
        return "redirect:/notes";
    }

    @GetMapping("/notes/upload")
    public String showUploadForm() {
        return "notes/upload";
    }

    @PostMapping("/notes/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("title") String title) {
        NoteDTO createdNote = notesApiClient.upload(file, title);
        return "redirect:/notes/" + createdNote.id();
    }
}