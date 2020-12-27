package com.company.simpljwt.controllers;

import com.company.simpljwt.commands.AuthRequestCommand;
import com.company.simpljwt.commands.AuthResponseCommand;
import com.company.simpljwt.config.security.jwt.JwtProvider;
import com.company.simpljwt.model.User;
import com.company.simpljwt.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    // {"login":"testlogin", "password":"testpassword"}
    @PostMapping("/auth")
    public AuthResponseCommand auth(@RequestBody AuthRequestCommand requestCommand) {
        User user = userService.findByLoginAndPassword(requestCommand.getLogin(), requestCommand.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponseCommand(token);
    }
}