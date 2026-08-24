package ru.krastti.entity.dto;

import java.time.LocalDate;

public record NoteDTO(int id, String title, String text, LocalDate creationTime) { }
