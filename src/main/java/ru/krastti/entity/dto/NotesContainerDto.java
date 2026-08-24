package ru.krastti.entity.dto;

import java.util.List;

public record NotesContainerDto(List<NoteDTO> notes) {
    public NotesContainerDto(List<NoteDTO> notes) {
        this.notes = notes;
    }
}
