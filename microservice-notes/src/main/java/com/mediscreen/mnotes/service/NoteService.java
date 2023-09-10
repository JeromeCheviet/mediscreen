package com.mediscreen.mnotes.service;

import com.mediscreen.mnotes.model.Note;

public interface NoteService {

    Iterable<Note> getNotesByPatId(String patId);


}
