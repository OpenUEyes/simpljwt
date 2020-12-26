package com.company.simpljwt.controllers;

import com.company.simpljwt.commands.UserCommand;
import com.company.simpljwt.model.Color;
import com.company.simpljwt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // {"age":"21", "name":"abc"}
    @ResponseBody
    @PostMapping({"/save", "/update"})
    public UserCommand save(@Valid @RequestBody UserCommand userCommand) {
        return userService.save(userCommand);
    }

    // http://localhost:8080/user/get/by/age?age=24
    @ResponseBody
    @GetMapping("/get/by/age")
    public Set<UserCommand> getByAge(@RequestParam int age) {
        return userService.findAllByAgeGreaterThanEqual(age);
    }

    // http://localhost:8080/user/get/by/color?color=red
    @ResponseBody
    @GetMapping("/get/by/color")
    public Set<UserCommand> getByColor(@RequestParam String color) {
        return userService.findAllByArticleColor(Color.valueOf(color.toUpperCase()));
    }

    // http://localhost:8080/user/get/by/counter?counter=2
    @ResponseBody
    @GetMapping("/get/by/counter")
    public Set<String> getByCounter(@RequestParam int counter) {
        return userService.findAllNamesByArticlesCount(counter);
    }
}