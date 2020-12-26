package com.company.simpljwt.services;

import com.company.simpljwt.commands.UserCommand;
import com.company.simpljwt.converters.UserUserCommandConverter;
import com.company.simpljwt.model.Color;
import com.company.simpljwt.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserUserCommandConverter mapper;

    public UserServiceImpl(UserRepository userRepository, UserUserCommandConverter mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserCommand save(UserCommand command) {
        return mapper.modelToCommand(userRepository.save(mapper.commandToModel(command)));
    }

    @Override
    public Set<UserCommand> findAllByAgeGreaterThanEqual(int age) {
        return new HashSet<>(mapper.modelToCommand(userRepository.findAllByAgeGreaterThanEqual(age)));

    }

    @Override
    public Set<UserCommand> findAllByArticleColor(Color color) {
        return new HashSet<>(mapper.modelToCommand(userRepository.findAllByArticleColor(color)));
    }

    @Override
    public Set<String> findAllNamesByArticlesCount(int counter) {
        Set<String> names = new HashSet<>();
        userRepository.findAllByArticlesCount(counter).forEach((user) -> names.add(user.getName()));
        return names;
    }
}