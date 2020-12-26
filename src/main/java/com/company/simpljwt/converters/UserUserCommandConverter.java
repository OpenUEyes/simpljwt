package com.company.simpljwt.converters;

import com.company.simpljwt.commands.UserCommand;
import com.company.simpljwt.model.User;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserUserCommandConverter {

    User commandToModel(UserCommand command);

    Collection<UserCommand> modelToCommand(Collection<User> users);

    UserCommand modelToCommand(User user);
}