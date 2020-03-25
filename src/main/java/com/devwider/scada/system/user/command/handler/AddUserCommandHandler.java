/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.command.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.Event;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.command.AddUserCommand;
import com.devwider.scada.system.user.event.AddedUserEvent;
import com.devwider.scada.cqrs.handler.CommandHandlable;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Kamil-Tomasz
 */
class AddUserCommandHandler extends AbstractUserCommandHandler implements CommandHandlable<AddUserCommand> {

    AddUserCommandHandler(Repository<User> reposiotry) {
        super(reposiotry);
    }

    @Override
    public CompletableFuture<Event> handle(AddUserCommand command) {
        User user = User.builder()
                .admin(command.isAdmin())
                .disabled(command.isDisabled())
                .email(command.getEmail())
                .password(command.getPassword())
                .phone(command.getPhone())
                .username(command.getUsername())
                .uuid(command.getUserUuid())
                .build();
        User userCreated = getReposiotry().create(user);
        return CompletableFuture.completedFuture(new AddedUserEvent(userCreated, command.getUuid()));
    }
}
