package com.company.simpljwt.controllers;

import com.company.simpljwt.commands.UserCommand;
import com.company.simpljwt.converters.UserUserCommandConverter;
import com.company.simpljwt.model.Color;
import com.company.simpljwt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserUserCommandConverter mapper;

    // {"login":"savelogin", "password":"savepassword", "age":"21", "name":"abc"}
    @ResponseBody
    @PostMapping({"/save"})
    public UserCommand save(@Valid @RequestBody UserCommand userCommand) {
        return mapper.modelToCommand(userService.save(mapper.commandToModel(userCommand)));
    }

    // http://localhost:8080/user/get/by/age?age=24
    @ResponseBody
    @GetMapping("/get/by/age")
    public Set<UserCommand> getByAge(@RequestParam int age) {
        return new HashSet<>(mapper.modelToCommand(userService.findAllByAgeGreaterThanEquals(age)));
    }

    // http://localhost:8080/user/get/by/color?color=red
    @ResponseBody
    @GetMapping("/get/by/color")
    public Set<UserCommand> getByColor(@RequestParam String color) {
        return new HashSet<>(mapper.modelToCommand(userService.findAllByArticleColor(Color.valueOf(color.toUpperCase()))));
    }

    // http://localhost:8080/user/get/by/counter?counter=2
    @ResponseBody
    @GetMapping("/get/by/counter")
    public Set<String> getByCounter(@RequestParam int counter) {
        return userService.findAllNamesByArticlesCount(counter);
    }
}