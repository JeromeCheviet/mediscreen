package com.mediscreen.mnotes.repository;

import com.mediscreen.mnotes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    Optional<Note> findAllById(String id);
    List<Note> findNotesByPatId(Integer patId);

}
