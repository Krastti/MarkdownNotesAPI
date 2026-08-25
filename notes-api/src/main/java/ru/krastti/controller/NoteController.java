package ru.krastti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.krastti.entity.dto.NoteDTO;
import ru.krastti.entity.dto.NotesContainerDto;
import ru.krastti.entity.dto.request.CreateNoteRequest;
import ru.krastti.entity.dto.request.UpdateNoteRequest;
import ru.krastti.service.NoteService;

@RestController
@RequestMapping("/api")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public NotesContainerDto findAll() {
        return noteService.findAll();
    }

    @GetMapping("/notes/{id}")
    public NoteDTO findById(@PathVariable int id) {
        return noteService.findById(id);
    }

    @PostMapping("/notes")
    public NoteDTO save(@RequestBody CreateNoteRequest request) {
        return noteService.save(request);
    }

    @PostMapping(
        value = "/notes/upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<NoteDTO> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title) {
        NoteDTO createdNote = noteService.uploadNote(file, title);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @PutMapping("/notes/{id}")
    public NoteDTO update(@PathVariable int id, @RequestBody UpdateNoteRequest request) {
        return noteService.update(id, request);
    }

    @DeleteMapping("/notes/{id}")
    public void delete(@PathVariable int id) {
        noteService.delete(id);
    }
}