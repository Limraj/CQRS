/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.command.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.Event;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.command.RemoveUserCommand;
import com.devwider.scada.cqrs.FailedCommandEvent;
import com.devwider.scada.system.user.event.RemovedUserEvent;
import com.devwider.scada.cqrs.handler.CommandHandlable;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Kamil-Tomasz
 */
class RemoveUserCommandHandler extends AbstractUserCommandHandler implements CommandHandlable<RemoveUserCommand> {

    RemoveUserCommandHandler(Repository<User> reposiotry) {
        super(reposiotry);
    }

    @Override
    public CompletableFuture<Event> handle(RemoveUserCommand command) {
        UUID uuid = command.getUserUuid();
        User user = getReposiotry().remove(uuid == null ? "" : uuid.toString());
        if(user != null) {
            return CompletableFuture.completedFuture(new RemovedUserEvent(user, command.getUuid()));
        }
        return CompletableFuture.completedFuture(new FailedCommandEvent(command));
    }
}
