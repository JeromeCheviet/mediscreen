package com.mediscreen.mnotes.service;

import com.mediscreen.mnotes.model.Note;

public interface NoteService {

    Iterable<Note> getNotesByPatId(Integer patId);
    void addNote(Note note);

    Note updateNote(Note note);

}
