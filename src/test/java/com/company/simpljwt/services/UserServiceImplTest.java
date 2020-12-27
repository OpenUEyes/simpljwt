package com.company.simpljwt.services;

import com.company.simpljwt.model.Role;
import com.company.simpljwt.model.User;
import com.company.simpljwt.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repositoryMock;

    @InjectMocks
    private UserServiceImpl service;


    @Test
    void saveShouldEncodePassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "password";

        String encodedPassword = passwordEncoder.encode(password);

        User encodedUser = User.builder().password(encodedPassword).build();
        when(repositoryMock.save(encodedUser)).thenReturn(encodedUser);

        User expectedUser = service.save(User.builder().password(password).build());
        assertTrue(passwordEncoder.matches(password, expectedUser.getPassword()));
    }

    @Test
    void saveShouldSetUserRole() {
        User roleUser = User.builder().password("password").role(Role.USER).build();
        when(repositoryMock.save(roleUser)).thenReturn(roleUser);

        User expectedUser = service.save(User.builder().password("password").build());
        assertSame(Role.USER, expectedUser.getRole());
    }

    @Test
    void findByLoginPasswordShouldMatchPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String login = "login";
        String password = "password";
        String encodedPassword = passwordEncoder.encode(password);
        User encodedUser = User.builder().login(login).password(encodedPassword).build();

        when(repositoryMock.findByLogin(login)).thenReturn(encodedUser);

        assertNotNull(service.findByLoginAndPassword(login,password));
    }

    @Test
    void findByLoginPasswordShouldNotMatchPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String login = "login";
        String password = "password";

        String wrongPassword = "wrongPassword";
        User user = User.builder().login(login).password(passwordEncoder.encode(wrongPassword)).build();
        when(repositoryMock.findByLogin(login)).thenReturn(user);

        assertNull(service.findByLoginAndPassword(login,password));
    }
}