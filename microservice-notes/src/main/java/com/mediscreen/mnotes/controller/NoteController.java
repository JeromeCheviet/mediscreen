package com.mediscreen.mnotes.controller;

import com.mediscreen.mnotes.model.Note;
import com.mediscreen.mnotes.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Class which manage REST API Controller for patient history.
 */
@CrossOrigin
@RestController
public class NoteController {
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    /**
     * Method to get all note to belongs to one patient
     *
     * @param patId Integer - The patient ID
     * @return an iterable list of Note object and the HTTP status 200
     */
    @GetMapping(value = "/patHistory/{patId}")
    public ResponseEntity<Iterable<Note>> getNoteByPatId(@PathVariable("patId") Integer patId) {
        logger.debug("Get /patHistory/{}", patId);
        return new ResponseEntity<>(noteService.getNotesByPatId(patId), HttpStatus.OK);
    }

    /**
     * Method to get a specific note
     *
     * @param noteId String - The id of the note
     * @return The note object and the HTTP status 200
     */
    @GetMapping(value = "/patHistory/note/{noteId}")
    public ResponseEntity<Note> getAllByNoteId(@PathVariable("noteId") String noteId) {
        logger.debug("Get /patHistory/note/{}", noteId);
        return new ResponseEntity<>(noteService.getNoteById(noteId), HttpStatus.OK);
    }

    /**
     * Method to create a note
     *
     * @param newNote the object model Note in json format
     * @return the created object Note and HTTP status 201
     */
    @PostMapping(value = "/patHistory/add")
    public ResponseEntity<Note> addNote(@RequestBody Note newNote) {
        logger.debug("Post /patHistory/add");
        noteService.addNote(newNote);

        return new ResponseEntity<>(newNote, HttpStatus.CREATED);
    }

    /**
     * Method to update an existing note
     *
     * @param updatedNote the object model Note in json format
     * @return the updated note and HTTP status 200
     */
    @PutMapping(value = "/patHistory")
    public ResponseEntity<Note> updateNote(@RequestBody Note updatedNote) {
        logger.debug("Put /patHistory");
        return new ResponseEntity<>(noteService.updateNote(updatedNote), HttpStatus.OK);
    }

    /**
     * Method to delete a note
     *
     * @param noteId String - the ID of the note
     * @return a string whose inform the id of deleted note and HTTP status 204
     */
    @DeleteMapping(value = "/patHistory/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable("noteId") String noteId) {
        logger.debug("Delete /patHistory/{}", noteId);
        noteService.deleteNote(noteId);

        return new ResponseEntity<>("Note " + noteId + " deleted", HttpStatus.NO_CONTENT);
    }
}
