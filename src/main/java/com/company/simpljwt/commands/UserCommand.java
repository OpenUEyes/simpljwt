package com.company.simpljwt.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCommand implements BaseCommand{

    @NotEmpty
    private int age;

    @NotEmpty
    private String name;

}