package com.mediscreen.mnotes.service;

import com.mediscreen.mnotes.exception.NoteNotCreatedException;
import com.mediscreen.mnotes.exception.NoteNotDeletedException;
import com.mediscreen.mnotes.exception.NoteNotFoundException;
import com.mediscreen.mnotes.exception.NoteNotUpdatedException;
import com.mediscreen.mnotes.model.Note;
import com.mediscreen.mnotes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Class to interact with Notes
 */
@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Autowired
    private NoteRepository noteRepository;

    /**
     * {@inheritDoc}
     *
     * <br>If note not exist, a custom exception is throwing
     */
    @Override
    public Iterable<Note> getNotesByPatId(Integer patId) {
        logger.info("Get all notes for patient id : {}", patId);
        Iterable<Note> notes = noteRepository.findNotesByPatId(patId);
        if (!notes.iterator().hasNext()) {
            throw new NoteNotFoundException("No note found for patient id : " + patId);
        }
        return notes;
    }

    /**
     * {@inheritDoc}
     *
     * <br>If note is not found, a custom exception is throwing
     */
    @Override
    public Note getNoteById(String noteId) {
        logger.debug("Get note with id {}", noteId);
        Optional<Note> note = noteRepository.findAllById(noteId);
        if (note.isEmpty()) {
            throw new NoteNotFoundException("Note with id " + noteId + " not found");
        }
        return note.get();
    }

    /**
     * {@inheritDoc}
     *
     * <br>If an error is occured when the note is saving, a custom exception is throwing
     */
    @Override
    public void addNote(Note note) {
        logger.debug("Add new note with patid: {}", note.getPatId());
        try {
            noteRepository.insert(note);
        } catch (Exception e) {
            throw new NoteNotCreatedException("Note not created. Reason : " + e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <br>After updating, the method compare the information from the database and from the json object.
     * If they are different, a custom exception is throwing
     */
    @Override
    public Note updateNote(Note note) {
        logger.debug("Update note with id {}", note.getId());
        noteRepository.save(note);

        Note updatedNote = getNoteById(note.getId());

        if (!note.toString().equals(updatedNote.toString())) {
            throw new NoteNotUpdatedException("Note with id " + note.getId() + " not updated");
        }

        return updatedNote;
    }

    /**
     * {@inheritDoc}
     *
     * <br>After deleting, the method try to get the note by her old id. If the id exist again in database,
     * a custom exception is throwing
     */
    @Override
    public void deleteNote(String noteId) {
        logger.debug("Delete note with id {}", noteId);
        noteRepository.deleteById(noteId);

        Optional<Note> deletedNote = noteRepository.findAllById(noteId);

        if (deletedNote.isPresent()) {
            throw new NoteNotDeletedException("Note with id " + noteId + " not deleted");
        }
    }


}
