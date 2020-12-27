package com.company.simpljwt.services;

import com.company.simpljwt.model.Color;
import com.company.simpljwt.model.User;

import java.util.Collection;
import java.util.Set;

public interface UserService extends CrudService<User, Long> {

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    Collection<User> findAllByAgeGreaterThanEquals(int age);

    Collection<User> findAllByArticleColor(Color color);

    Set<String> findAllNamesByArticlesCount(int counter);
}