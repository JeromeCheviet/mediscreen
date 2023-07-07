package com.mediscreen.mpatient.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerService {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    private void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Sql(scripts = "/insert_data.sql")
    @Sql(scripts = "/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void testGetAllPatients_mustReturnIsOk() throws Exception {
        mvc.perform(get("/patient"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void testGetAllPatients_mustReturnNotFound_whenPatientTableIsEmpty() throws Exception {
        mvc.perform(get("/patient"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Patient not found"));
    }

    @Sql(scripts = "/insert_data.sql")
    @Sql(scripts = "/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void testGetPatient_mustReturnIsOk() throws Exception {
        String expectedContent = "{" +
                "\"patientId\":1" +
                ",\"family\":\"John\"" +
                ",\"given\":\"Doe\"" +
                ",\"dob\":\"1980-09-02\"" +
                ",\"sex\":\"M\"" +
                ",\"address\":\"1 rue du Pont\"" +
                ",\"phone\":\"000-111-1111\"" +
                "}";

        mvc.perform(get("/patient/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedContent));
    }

    @Test
    void testGetPatient_mustReturnNotFound_whenTableIsEmpty() throws Exception {
        mvc.perform(get("/patient/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Patient not found"));
    }

    @Test
    void testGetPatient_mustReturnNotFound_whenUserNotExist() throws Exception {
        mvc.perform(get("/patient/100"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Patient not found"));
    }

    @Sql(scripts = "/insert_data.sql")
    @Sql(scripts = "/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void testAddPatient_mustReturnCreated() throws Exception {
        String expectedContent = "{" +
                "\"family\":\"Jerome\"" +
                ",\"given\":\"Doe\"" +
                ",\"dob\":\"2010-09-25\"" +
                ",\"sex\":\"M\"" +
                ",\"address\":\"30 rue du Puit\"" +
                ",\"phone\":\"000-555-7777\"" +
                "}";

        mvc.perform(post("/patient/add")
                        .content(expectedContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"patientId\":3,\"family\":\"Jerome\",\"given\":\"Doe\",\"dob\":\"2010-09-25\",\"sex\":\"M\",\"address\":\"30 rue du Puit\",\"phone\":\"000-555-7777\"}"));
    }

    @Test
    void testAddPatient_mustReturnBadRequest_whenDobIsInFuture() throws Exception {
        String expectedContent = "{" +
                "\"family\":\"Jerome\"" +
                ",\"given\":\"Doe\"" +
                ",\"dob\":\"2100-09-25\"" +
                ",\"sex\":\"M\"" +
                ",\"address\":\"30 rue du Puit\"" +
                ",\"phone\":\"000-555-7777\"" +
                "}";

        mvc.perform(post("/patient/add")
                        .content(expectedContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid request content."));
    }

    @Test
    void testAddPatient_mustReturnBadRequest_whenDobIsMissing() throws Exception {
        String expectedContent = "{" +
                "\"family\":\"Jerome\"" +
                ",\"given\":\"Doe\"" +
                ",\"sex\":\"M\"" +
                ",\"address\":\"30 rue du Puit\"" +
                ",\"phone\":\"000-555-7777\"" +
                "}";

        mvc.perform(post("/patient/add")
                        .content(expectedContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid request content."));
    }

    @Test
    void testAddPatient_mustReturnBadRequest_whenSexIsNotMorF() throws Exception {
        String expectedContent = "{" +
                "\"family\":\"Jerome\"" +
                ",\"given\":\"Doe\"" +
                ",\"dob\":\"2010-09-25\"" +
                ",\"sex\":\"Male\"" +
                ",\"address\":\"30 rue du Puit\"" +
                ",\"phone\":\"000-555-7777\"" +
                "}";

        mvc.perform(post("/patient/add")
                        .content(expectedContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid request content."));
    }

    @Test
    void testAddPatient_mustReturnBadRequest_whenPhoneIsMalformed() throws Exception {
        String expectedContent = "{" +
                "\"family\":\"Jerome\"" +
                ",\"given\":\"Doe\"" +
                ",\"dob\":\"2010-09-25\"" +
                ",\"sex\":\"M\"" +
                ",\"address\":\"30 rue du Puit\"" +
                ",\"phone\":\"000-5556-7777\"" +
                "}";

        mvc.perform(post("/patient/add")
                        .content(expectedContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Invalid request content."));
    }

    @Sql(scripts = "/insert_data.sql")
    @Sql(scripts = "/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void testDeletePatient_mustReturnNoContent() throws Exception {
        MvcResult mvcResult = mvc.perform(delete("/patient/1"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contentEquals("Patient 1 deleted"));
    }
}
