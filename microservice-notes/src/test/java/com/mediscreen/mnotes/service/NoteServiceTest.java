package com.mediscreen.mnotes.service;

import com.mediscreen.mnotes.model.Note;
import com.mediscreen.mnotes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {
    @InjectMocks
    private NoteService noteService = new NoteServiceImpl();

    @Mock
    private NoteRepository noteRepository;

    private String expectedId = "12345678abcdef";
    private String expectedPatId = "1";
    private String expectedPatNote = "My first note";

    private Note expectedNote = new Note();

    @BeforeEach
    private void setUp() {
        expectedNote.setId(expectedId);
        expectedNote.setPatId(expectedPatId);
        expectedNote.setNotes(expectedPatNote);
    }

    @Test
    void testGetNoteByPatId() {
        Note expectedNote2 = new Note("abcdef12345678", expectedPatId,"My second note");
        List<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(expectedNote);
        expectedNotes.add(expectedNote2);

        when(noteRepository.findNotesByPatId(expectedPatId)).thenReturn(expectedNotes);

        Iterable<Note> actualNotes = noteRepository.findNotesByPatId("1");

        assertEquals(expectedNotes, actualNotes);
        verify(noteRepository, times(1)).findNotesByPatId(expectedPatId);
    }
}
