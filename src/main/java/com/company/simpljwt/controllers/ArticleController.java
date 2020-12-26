package com.company.simpljwt.controllers;

import com.company.simpljwt.commands.ArticleCommand;
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

    //    {"color":"RED", "text":"textABCDE", "user":{"id":"1"}}
    @ResponseBody
    @PostMapping({"/save", "/update"})
    public ArticleCommand save(@Valid @RequestBody ArticleCommand articleCommand) {
        return articleService.save(articleCommand);
    }
}