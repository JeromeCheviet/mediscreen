package com.mediscreen.mnotes.service;

import com.mediscreen.mnotes.model.Note;
import com.mediscreen.mnotes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Iterable<Note> getNotesByPatId(Integer patId) {
        logger.debug("Get all notes for patient id : {}", patId);
        Iterable<Note> notes = noteRepository.findNotesByPatId(patId);
        if (!notes.iterator().hasNext()) {
            logger.info("No notes found for patient {}", patId);
        }
        return notes;
    }

    @Override
    public void addNote(Note note) {
        logger.debug("Add new note with patid: {}", note.getPatId());
        try {
            noteRepository.insert(note);
        } catch (Exception e) {
            logger.error("Note not created. Reason : " + e);
        }
    }

    @Override
    public Note updateNote(Note note) {
        logger.debug("Update note with id {}", note.getId());
        noteRepository.save(note);

        Note updatedNote = noteRepository.findAllById(note.getId());

        if (!note.toString().equals(updatedNote.toString())) {
            logger.error("Note with id " + note.getId() + " not updated");
        }

        return updatedNote;
    }


}
