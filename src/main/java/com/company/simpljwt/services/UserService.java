package com.company.simpljwt.services;

import com.company.simpljwt.commands.UserCommand;
import com.company.simpljwt.model.Color;

import java.util.Set;

public interface UserService extends CrudService<UserCommand, Long> {

    Set<UserCommand> findAllByAgeGreaterThanEqual(int age);

    Set<UserCommand> findAllByArticleColor(Color color);

    Set<String> findAllNamesByArticlesCount(int counter);
}