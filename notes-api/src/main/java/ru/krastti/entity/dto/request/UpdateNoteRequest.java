package ru.krastti.entity.dto.request;

import ru.krastti.entity.Note;

import java.time.LocalDateTime;

public class UpdateNoteRequest {
    private final String title;
    private final String text;

    public UpdateNoteRequest(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Note toEntity(int id, LocalDateTime creationTime) {
        return new Note(id, title, text, creationTime);
    }
}
