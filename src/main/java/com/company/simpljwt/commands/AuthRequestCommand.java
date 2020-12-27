package com.company.simpljwt.commands;

import lombok.Data;

@Data
public class AuthRequestCommand {

    private String login;
    private String password;
}