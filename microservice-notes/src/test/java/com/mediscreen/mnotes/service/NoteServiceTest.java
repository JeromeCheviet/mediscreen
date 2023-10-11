package com.mediscreen.mnotes.service;

import com.mediscreen.mnotes.exception.NoteNotCreatedException;
import com.mediscreen.mnotes.exception.NoteNotDeletedException;
import com.mediscreen.mnotes.exception.NoteNotFoundException;
import com.mediscreen.mnotes.exception.NoteNotUpdatedException;
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
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {
    @InjectMocks
    private NoteService noteService = new NoteServiceImpl();

    @Mock
    private NoteRepository noteRepository;

    private String expectedId = "12345678abcdef";
    private Integer expectedPatId = 1;
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
        Note expectedNote2 = new Note("abcdef12345678", expectedPatId, "My second note");
        List<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(expectedNote);
        expectedNotes.add(expectedNote2);

        when(noteRepository.findNotesByPatId(expectedPatId)).thenReturn(expectedNotes);

        Iterable<Note> actualNotes = noteRepository.findNotesByPatId(1);

        assertEquals(expectedNotes, actualNotes);
        verify(noteRepository, times(1)).findNotesByPatId(expectedPatId);
    }

    @Test
    void testGetNoteByPatId_throwException_WhenNoNotes() {
        when(noteRepository.findNotesByPatId(expectedPatId)).thenReturn(EMPTY_LIST);

        Throwable exception = assertThrows(NoteNotFoundException.class, () -> noteService.getNotesByPatId(1));

        assertEquals("No note found for patient id : 1", exception.getMessage());
        verify(noteRepository, times(1)).findNotesByPatId(1);
    }

    @Test
    void testGetNoteById() {
        when(noteRepository.findAllById(expectedNote.getId())).thenReturn(Optional.of(expectedNote));

        Note actualNote = noteService.getNoteById("12345678abcdef");

        assertEquals(expectedNote.toString(), actualNote.toString());
    }

    @Test
    void testGetNoteById_throwException_WhenNoNote() {
        when(noteRepository.findAllById(expectedNote.getId())).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById("12345678abcdef"));

        assertEquals("Note with id 12345678abcdef not found", exception.getMessage());
        verify(noteRepository, times(1)).findAllById("12345678abcdef");
    }

    @Test
    void testAddNote() {
        when(noteRepository.insert(any(Note.class))).thenReturn(expectedNote);

        noteService.addNote(expectedNote);

        verify(noteRepository, times(1)).insert(expectedNote);
    }

    @Test
    void testAddNote_throwException_whenNoteNotAdded() {
        when(noteRepository.insert(any(Note.class))).thenThrow(RuntimeException.class);

        Throwable exception = assertThrows(NoteNotCreatedException.class, () -> noteService.addNote(expectedNote));

        assertEquals("Note not created. Reason : java.lang.RuntimeException", exception.getMessage());
        verify(noteRepository, times(1)).insert(expectedNote);
    }

    @Test
    void testUpdateNote() {
        expectedNote.setNotes("Updated note");

        when(noteRepository.save(any(Note.class))).thenReturn(expectedNote);
        when(noteRepository.findAllById(expectedNote.getId())).thenReturn(Optional.of(expectedNote));

        Note actualNote = noteService.updateNote(expectedNote);

        assertEquals(expectedNote.getNotes(), actualNote.getNotes());
        verify(noteRepository, times(1)).save(expectedNote);
        verify(noteRepository, times(1)).findAllById(expectedNote.getId());
    }

    @Test
    void testUpdateNote_throwException_whenUpdateFailed() {
        Note updatedNote = new Note(
                expectedId,
                expectedPatId,
                "Updated Note"
        );

        when(noteRepository.save(any(Note.class))).thenReturn(expectedNote);
        when(noteRepository.findAllById(updatedNote.getId())).thenReturn(Optional.of(expectedNote));

        Throwable exception = assertThrows(NoteNotUpdatedException.class, () -> noteService.updateNote(updatedNote));

        assertEquals("Note with id 12345678abcdef not updated", exception.getMessage());
        verify(noteRepository, times(1)).save(updatedNote);
        verify(noteRepository, times(1)).findAllById("12345678abcdef");
    }

    @Test
    void testDeleteNote() {
        doNothing().when(noteRepository).deleteById(expectedNote.getId());
        when(noteRepository.findAllById(expectedNote.getId())).thenReturn(Optional.empty());

        noteService.deleteNote(expectedNote.getId());

        verify(noteRepository, times(1)).deleteById(expectedNote.getId());
        verify(noteRepository, times(1)).findAllById(expectedNote.getId());
    }

    @Test
    void testDeleteNote_throwException_whenPatientAlwaysFound() {
        doNothing().when(noteRepository).deleteById(expectedNote.getId());
        when(noteRepository.findAllById(expectedNote.getId())).thenReturn(Optional.of(expectedNote));

        Throwable exception = assertThrows(NoteNotDeletedException.class, () -> noteService.deleteNote(expectedNote.getId()));

        assertEquals("Note with id 12345678abcdef not deleted", exception.getMessage());
        verify(noteRepository, times(1)).deleteById(expectedNote.getId());
        verify(noteRepository, times(1)).findAllById(expectedNote.getId());
    }
}
