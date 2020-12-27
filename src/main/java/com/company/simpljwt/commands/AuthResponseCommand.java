package com.company.simpljwt.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseCommand {

    private String token;
}