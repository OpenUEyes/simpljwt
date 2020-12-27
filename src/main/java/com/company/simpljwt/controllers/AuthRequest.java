package com.company.simpljwt.controllers;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}