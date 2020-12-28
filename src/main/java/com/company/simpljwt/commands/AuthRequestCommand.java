package com.company.simpljwt.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthRequestCommand {

    private String login;
    private String password;
}