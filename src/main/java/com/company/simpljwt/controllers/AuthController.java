package com.company.simpljwt.controllers;

import com.company.simpljwt.config.jwt.JwtProvider;
import com.company.simpljwt.model.Account;
import com.company.simpljwt.services.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    private final AccountService accountService;
    private final JwtProvider jwtProvider;

    public AuthController(AccountService accountService, JwtProvider jwtProvider) {
        this.accountService = accountService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        Account userEntity = new Account();
        userEntity.setPassword(registrationRequest.getPassword());
        userEntity.setLogin(registrationRequest.getLogin());
        accountService.saveAccount(userEntity);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        Account userEntity = accountService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
}
