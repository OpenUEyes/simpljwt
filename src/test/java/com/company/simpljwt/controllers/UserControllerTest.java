package com.company.simpljwt.controllers;

import com.company.simpljwt.commands.UserCommand;
import com.company.simpljwt.converters.UserUserCommandConverter;
import com.company.simpljwt.model.Role;
import com.company.simpljwt.model.User;
import com.company.simpljwt.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    UserUserCommandConverter userUserCommandConverter;

    @Test
    public void save() throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "password";

        UserCommand requestUserCommand = UserCommand.builder()
                .login("login")
                .password(password)
                .name("name")
                .age(37)
                .build();

        User inputUser = User.builder()
                .login("login")
                .password(password)
                .name("name")
                .age(37)
                .build();

        User savedUser = User.builder()
                .id(1L)
                .login("login")
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .name("name")
                .age(37)
                .build();

        UserCommand responseUserCommand = UserCommand.builder()
                .login("login")
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .name("name")
                .age(37)
                .build();

        when(userUserCommandConverter.commandToModel(requestUserCommand)).thenReturn(inputUser);
        when(userService.save(inputUser)).thenReturn(savedUser);
        when(userUserCommandConverter.modelToCommand(savedUser)).thenReturn(responseUserCommand);

        this.mockMvc.perform(post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(inputUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(mapper.writeValueAsString(responseUserCommand))));
    }
}