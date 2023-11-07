package com.mediscreen.mnotes.controller;

import com.mediscreen.mnotes.model.Note;
import com.mediscreen.mnotes.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class NoteController {
    private Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @GetMapping(value = "/patHistory/{patId}")
    public ResponseEntity<Iterable<Note>> getNoteByPatId(@PathVariable("patId") Integer patId) {
        logger.debug("Get /patHistory/{}", patId);
        return new ResponseEntity<>(noteService.getNotesByPatId(patId), HttpStatus.OK);
    }

    @GetMapping(value = "/patHistory/note/{noteId}")
    public ResponseEntity<Note> getAllByNoteId(@PathVariable("noteId") String noteId) {
        logger.debug("Get /patHistory/note/{}", noteId);
        return new ResponseEntity<>(noteService.getNoteById(noteId), HttpStatus.OK);
    }

    @PostMapping(value = "/patHistory/add")
    public ResponseEntity<Note> addNote(@RequestBody Note newNote) {
        logger.debug("Post /patHistory/add");
        noteService.addNote(newNote);

        return new ResponseEntity<>(newNote, HttpStatus.CREATED);
    }

    @PutMapping(value = "/patHistory")
    public ResponseEntity<Note> updateNote(@RequestBody Note updatedNote) {
        logger.debug("Put /patHistory");
        return new ResponseEntity<>(noteService.updateNote(updatedNote), HttpStatus.OK);
    }

    @DeleteMapping(value = "/patHistory/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable("noteId") String noteId) {
        logger.debug("Delete /patHistory/{}", noteId);
        noteService.deleteNote(noteId);

        return new ResponseEntity<>("Note " + noteId + " deleted", HttpStatus.NO_CONTENT);
    }
}
