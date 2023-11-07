package com.mediscreen.mnotes.controller;

import com.mediscreen.mnotes.model.Note;
import com.mediscreen.mnotes.service.NoteService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private NoteService noteService;

    @BeforeEach
    private void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @Order(1)
    void testAddNote_mustReturnCreated() throws Exception {
        String expectedContent = "{" +
                "\"patId\":1," +
                "\"notes\":\"My first note\"" +
                "}";

        MvcResult mvcResult = mvc.perform(post("/patHistory/add")
                        .content(expectedContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("My first note"));

    }

    @Test
    @Order(2)
    void testGetNoteByPatId_mustReturnIsOk() throws Exception {
        String expectedNote = "My first note";

        MvcResult mvcResult = mvc.perform(get("/patHistory/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(expectedNote));
    }

    @Test
    @Order(3)
    void testGetNoteByPatId_mustReturnNotFound_whenPatIdNotExist() throws Exception {
        mvc.perform(get("/patHistory/2"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Note not found"));
    }

    @Test
    @Order(4)
    void testGetAllByNoteId() throws Exception {
        Iterable<Note> expectedNotes = noteService.getNotesByPatId(1);
        Note note = expectedNotes.iterator().next();
        String expectedNoteId = note.getId();

        MvcResult mvcResult = mvc.perform(get("/patHistory/note/" + expectedNoteId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("My first note"));
    }

    @Test
    @Order(5)
    void testGetAllByNoteId_mustReturnNotFound_whenNoteIdNotExist() throws Exception {
        mvc.perform(get("/patHistory/note/badnoteid"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Note not found"));
    }

    @Test
    @Order(6)
    void testUpdateNote_mustReturnOk() throws Exception {
        Iterable<Note> expectedNotes = noteService.getNotesByPatId(1);
        Note note = expectedNotes.iterator().next();
        String expectedContent = "{" +
                "\"id\":\"" + note.getId() + "\"," +
                "\"patId\":1," +
                "\"notes\":\"Updated note\"" +
                "}";

        mvc.perform(put("/patHistory")
                        .content(expectedContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"id\":\"" + note.getId() + "\",\"patId\":1,\"notes\":\"Updated note\"}"));
    }

    @Test
    @Order(7)
    void testDeleteNote_mustReturnNoContent() throws Exception {
        Iterable<Note> expectedNotes = noteService.getNotesByPatId(1);
        Note note = expectedNotes.iterator().next();
        MvcResult mvcResult = mvc.perform(delete("/patHistory/" + note.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contentEquals("Note " + note.getId() + " deleted"));
    }
}
