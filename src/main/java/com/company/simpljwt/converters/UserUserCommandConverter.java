package com.company.simpljwt.converters;

import com.company.simpljwt.commands.UserCommand;
import com.company.simpljwt.model.User;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UserUserCommandConverter {

    User commandToModel(UserCommand command);

    UserCommand modelToCommand(User user);

    Collection<UserCommand> modelToCommand(Collection<User> users);
}