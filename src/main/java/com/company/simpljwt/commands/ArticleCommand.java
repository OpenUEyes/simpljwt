package com.company.simpljwt.commands;

import com.company.simpljwt.model.Color;
import com.company.simpljwt.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleCommand implements BaseCommand {

    @NotEmpty
    private Color color;

    @NotEmpty
    private String text;

    @NotEmpty
    private User user;

}