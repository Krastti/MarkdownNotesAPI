package ru.krastti.entity;

import jakarta.persistence.*;
import ru.krastti.entity.dto.NoteDTO;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Entity
@Table(name = "Notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 30)
    private String title;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    public Note() { }

    public Note(int id, String title, String text, LocalDateTime creationTime) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationTime = creationTime;
    }

    public Note(String title, String text, LocalDateTime creationTime) {
        this.title = title;
        this.text = text;
        this.creationTime = creationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public NoteDTO toDto() {
        return new NoteDTO(id, title, text, creationTime.toLocalDate());
    };
}
