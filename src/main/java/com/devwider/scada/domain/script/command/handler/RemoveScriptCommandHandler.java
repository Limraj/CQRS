/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.command.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.Event;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.command.RemoveScriptCommand;
import com.devwider.scada.domain.script.event.RemovedScriptEvent;
import com.devwider.scada.cqrs.handler.CommandHandlable;
import com.devwider.scada.cqrs.FailedCommandEvent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Kamil-Tomasz
 */
class RemoveScriptCommandHandler extends AbstractScriptCommandHandler implements CommandHandlable<RemoveScriptCommand> {

    RemoveScriptCommandHandler(Repository<Script> reposiotry) {
        super(reposiotry);
    }

    @Override
    public CompletableFuture<Event> handle(RemoveScriptCommand command) {
        UUID uuid = command.getScriptUuid();
        if(uuid == null) {
            return CompletableFuture.completedFuture(new FailedCommandEvent(command));
        }
        String uuidString = uuid.toString();
        Script script = getReposiotry().remove(uuidString);
        if(script == null) {
            return CompletableFuture.completedFuture(new FailedCommandEvent(command));
        }
        return CompletableFuture.completedFuture(new RemovedScriptEvent(script, command.getUuid()));
    }
    
}
