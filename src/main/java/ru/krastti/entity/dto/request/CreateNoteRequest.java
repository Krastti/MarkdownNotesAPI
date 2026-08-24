package ru.krastti.entity.dto.request;

import ru.krastti.entity.Note;

import java.time.LocalDateTime;

public class CreateNoteRequest {
    private final String title;
    private final String text;

    public CreateNoteRequest(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Note toEntity() {
        return new Note(
                title,
                text,
                LocalDateTime.now()
        );
    }
}
