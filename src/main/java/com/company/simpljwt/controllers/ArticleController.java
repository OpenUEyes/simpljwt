package com.company.simpljwt.controllers;

import com.company.simpljwt.commands.ArticleCommand;
import com.company.simpljwt.converters.ArticleArticleCommandConverter;
import com.company.simpljwt.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleArticleCommandConverter mapper;

    //    {"color":"RED", "text":"textABCDE", "user":{"id":"1"}}
    @ResponseBody
    @PostMapping({"/save"})
    public ArticleCommand save(@Valid @RequestBody ArticleCommand articleCommand) {
        return mapper.modelToCommand(articleService.save(mapper.commandToModel(articleCommand)));
    }
}