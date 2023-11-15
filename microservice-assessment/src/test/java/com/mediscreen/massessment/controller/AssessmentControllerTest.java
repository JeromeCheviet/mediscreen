package com.mediscreen.massessment.controller;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
@AutoConfigureMockMvc
@SpringBootTest()
class AssessmentControllerTest {
    @RegisterExtension
    static WireMockExtension PATIENT = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().bindAddress("localhost").port(8081))
            .build();

    @RegisterExtension
    static WireMockExtension NOTES = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().bindAddress("localhost").port(8082))
            .build();

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

    @Test
    void testGetAssessment_mustReturnIsOk() throws Exception {
        String patientResponseBody = "{\"patientId\":1,\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}";
        String noteResponseBody = "[{\"id\":\"65463382450a4068f8be8c7b\",\"patId\":1,\"notes\":\"It's a note with an update\"},{\"id\":\"654a712a736a4575eaa7e0b5\",\"patId\":5,\"notes\":\"a new note\"}]";

        PATIENT.stubFor(WireMock.get("/patient/1").willReturn(WireMock.okJson(patientResponseBody)));
        NOTES.stubFor(WireMock.get("/patHistory/1").willReturn(WireMock.okJson(noteResponseBody)));

        MvcResult mvcResult = mvc.perform(get("/assess/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("NONE"));
    }

    @Test
    void testGetAssessment_mustReturnNotFound_whenPatientNotFound() throws Exception {
        PATIENT.stubFor(WireMock.get("/patient/1000").willReturn(WireMock.notFound()));

        mvc.perform(get("/assess/1000"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAssessment_mustReturnNotFound_whenNoteNotFound() throws Exception {
        String patientResponseBody = "{\"patientId\":1,\"family\":\"TestNone\",\"given\":\"Test\",\"dob\":\"1966-12-31\",\"sex\":\"F\",\"address\":\"1 Brookside St\",\"phone\":\"100-222-3333\"}";

        PATIENT.stubFor(WireMock.get("/patient/1").willReturn(WireMock.okJson(patientResponseBody)));
        NOTES.stubFor(WireMock.get("/patHistory/1").willReturn(WireMock.notFound()));

        mvc.perform(get("/assess/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
