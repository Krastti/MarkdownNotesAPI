package ru.krastti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.krastti.entity.Note;
import ru.krastti.entity.dto.NoteDTO;
import ru.krastti.entity.dto.NotesContainerDto;
import ru.krastti.entity.dto.request.CreateNoteRequest;
import ru.krastti.entity.dto.request.UpdateNoteRequest;
import ru.krastti.repository.NoteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NotesContainerDto findAll() {
        List<NoteDTO> notes = noteRepository.findAll().stream()
                .map(Note::toDto).collect(Collectors.toList());
        return new NotesContainerDto(notes);
    }

    public NoteDTO save(CreateNoteRequest request) {
        Note note = request.toEntity();
        return noteRepository.save(note).toDto();
    }

    public NoteDTO update(int id, UpdateNoteRequest request) {
        return noteRepository.findById(id)
                .map(existingPost -> {
                    Note updateNote = request.toEntity(existingPost.getId(), existingPost.getCreationTime());
                    return noteRepository.save(updateNote).toDto();
                }).orElseThrow(() -> new IllegalArgumentException("Note with " + id + " not found"));

    }

    public void delete(int id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Note with " + id + " not found"));
        noteRepository.delete(note);
    }
}
