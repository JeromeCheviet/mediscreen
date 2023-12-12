package com.mediscreen.mnotes.service;

import com.mediscreen.mnotes.model.Note;

/**
 * Interface link to Note operations
 */
public interface NoteService {

    /**
     * Get notes by patient ID
     *
     * @param patId patient id integer number
     * @return an iterable list of patient note
     */
    Iterable<Note> getNotesByPatId(Integer patId);

    /**
     * Get one note by his ID
     *
     * @param noteId Note id string
     * @return an object which contain the note
     */
    Note getNoteById(String noteId);

    /**
     * Add one note
     *
     * @param note an object which contain the note to add
     */
    void addNote(Note note);

    /**
     * Update one note
     *
     * @param note an object which contain note's data to update
     * @return an object which contain the note updated
     */
    Note updateNote(Note note);

    /**
     * Delete one note
     *
     * @param noteId the id string of note to delete
     */
    void deleteNote(String noteId);
}
