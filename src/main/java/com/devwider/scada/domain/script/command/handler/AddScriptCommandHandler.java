/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.command.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.Event;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.command.AddScriptCommand;
import com.devwider.scada.domain.script.event.AddedScriptEvent;
import com.devwider.scada.cqrs.handler.CommandHandlable;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Kamil-Tomasz
 */
class AddScriptCommandHandler extends AbstractScriptCommandHandler implements CommandHandlable<AddScriptCommand> {

    AddScriptCommandHandler(Repository<Script> reposiotry) {
        super(reposiotry);
    }

    @Override
    public CompletableFuture<Event> handle(AddScriptCommand command) {
        Script script = Script.builder()
                .name(command.getName())
                .xid(command.getXid())
                .userUuid(command.getUserUuid())
                .script(command.getScript())
                .uuid(command.getScriptUuid())
                .build();
        Script scriptCreated = getReposiotry().create(script);
        return CompletableFuture.completedFuture(new AddedScriptEvent(scriptCreated, command.getUuid()));
    }
    
}
