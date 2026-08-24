package ru.krastti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krastti.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
}
