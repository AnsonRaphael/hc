package com.nanos.irctc.controller;

import com.nanos.irctc.entity.user.Role;
import com.nanos.irctc.entity.user.User;
import com.nanos.irctc.model.user.UserDTO;
import com.nanos.irctc.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserDTO outUserDTO;

    @BeforeEach
    void setUp() {
        outUserDTO =UserDTO.builder()
                .role(Role.USER)
                .userId(1L)
                .userName("user1")
                .password("password")
                .email("user1@email.com")
                .build();

    }

    @Test
    void addUser() throws Exception {
        UserDTO inputUserDTO = UserDTO.builder()
                .role(Role.USER)
                .userName("user1")
                .password("password")
                .email("user1@email.com")
                .build();
        Mockito.when(userService.saveUser(inputUserDTO))
                .thenReturn(outUserDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"userName\":\"user1\",\n" +
                        "    \"email\":\"user1@email.com\",\n" +
                        "    \"password\":\"password\",\n" +
                        "    \"role\":\"USER\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getAllUser() throws Exception {
        Mockito.when(userService.getAllUser())
                .thenReturn(Arrays.asList(outUserDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(jsonPath());
                /*
         .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(3)))
                .andExpect(jsonPath("$.errors", hasItem("Author is not allowed.")))
                .andExpect(jsonPath("$.errors", hasItem("Please provide a price")));
                .andExpect(jsonPath("$.errors", hasItem("Please provide a author")))*/
    }
}