package com.company.simpljwt.services;

import com.company.simpljwt.model.Color;
import com.company.simpljwt.model.Role;
import com.company.simpljwt.model.User;
import com.company.simpljwt.repositories.UserRepository;
import lombok.extern.java.Log;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Log
@Service
public class UserServiceImpl implements com.company.simpljwt.services.UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public User save(User user) {
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Collection<User> findAllByAgeGreaterThanEquals(int age) {
        return userRepository.findAllByAgeGreaterThanEquals(age);

    }

    @Override
    public Collection<User> findAllByArticleColor(Color color) {
        return userRepository.findAllByArticleColor(color);
    }

    @Override
    public Set<String> findAllNamesByArticlesCount(int counter) {
        Set<String> names = new HashSet<>();
        userRepository.findAllByArticlesCount(counter).forEach((user) -> names.add(user.getName()));
        return names;
    }
}