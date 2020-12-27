package com.company.simpljwt.commands;

import com.company.simpljwt.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCommand implements BaseCommand {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    private Role role;

    @NotEmpty
    private int age;

    @NotEmpty
    private String name;
}