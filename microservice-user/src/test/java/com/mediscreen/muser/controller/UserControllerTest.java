package com.mediscreen.muser.controller;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
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
    void testGetAllUsers_mustReturnIsOk() throws Exception {
        mvc.perform(get("/v1/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void testGetAllUsers_mustReturnNotFound_whenUserTableIsEmpty() throws Exception {
        mvc.perform(get("/v1/users"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason("User not found"));
    }

    @Sql(scripts = "/insert_data.sql")
    @Sql(scripts = "/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void testGetUser_mustReturnIsOk() throws Exception {
        String expectedContent = "{" +
                "\"userID\":1" +
                ",\"firstName\":\"John\"" +
                ",\"lastName\":\"Doe\"" +
                ",\"birthDate\":\"1980-09-02\"" +
                ",\"gender\":\"Male\"" +
                ",\"address\":\"1 rue du Pont\"" +
                ",\"phoneNumber\":\"000-111\"" +
                "}";

        mvc.perform(get("/v1/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(expectedContent));

    }

    @Test
    void testGetUser_mustReturnNotFound_whenTableIsEmpty() throws Exception {
        mvc.perform(get("/v1/user/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason("User not found"));
    }

    @Sql(scripts = "/insert_data.sql")
    @Sql(scripts = "/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void testGetUser_mustReturnNotFound_whenUserNotExist() throws Exception {
        mvc.perform(get("/v1/user/100"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason("User not found"));
    }

    @Sql(scripts = "/insert_data.sql")
    @Sql(scripts = "/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void testAddUser_mustReturnCreated() throws Exception {
        String expectedContent = "{" +
                "\"firstName\":\"Jerome\"" +
                ",\"lastName\":\"Doe\"" +
                ",\"birthDate\":\"2010-09-25\"" +
                ",\"gender\":\"Male\"" +
                ",\"address\":\"30 rue du Puit\"" +
                ",\"phoneNumber\":\"000-555\"" +
                "}";

        mvc.perform(post("/v1/user/add")
                        .content(expectedContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"userID\":3,\"firstName\":\"Jerome\",\"lastName\":\"Doe\",\"birthDate\":\"2010-09-25\",\"gender\":\"Male\",\"address\":\"30 rue du Puit\",\"phoneNumber\":\"000-555\"}"));
    }

    @Test
    void testAddUser_mustReturnBadRequest_whenBirthdateInFuture() throws Exception {
        String expectedContent = "{" +
                "\"firstName\":\"Jerome\"" +
                ",\"lastName\":\"Doe\"" +
                ",\"birthDate\":\"2100-09-25\"" +
                ",\"gender\":\"Male\"" +
                ",\"address\":\"30 rue du Puit\"" +
                ",\"phoneNumber\":\"000-555\"" +
                "}";

        mvc.perform(post("/v1/user/add")
                        .content(expectedContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf8"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
