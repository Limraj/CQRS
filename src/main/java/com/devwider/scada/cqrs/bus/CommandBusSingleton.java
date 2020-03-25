/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.Event;
import com.devwider.scada.cqrs.handler.CommandHandlable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

/**
 *
 * @author Kamil-Tomasz
 */

enum CommandBusSingleton implements CommandBus {
    
    INSTANCE;
    
    private static final Repository<Command> repository = Commands.INSTANCE;
    private static final EventBus eventBus = EventBusFactory.getInstance();

    @Override
    public void execute(Command command) {
        Command commandCreated = repository.create(command);
        CommandHandlable<Command> handler = CommandHandlerProvider.handler(commandCreated.getClass());
        CompletableFuture<Event> event = handler.handle(commandCreated);
        try {
            eventBus.execute(event.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Command command, Command... commands) {
        execute(command);
        Stream.of(commands).forEach(this::execute);
    }

    @Override
    public List<Command> readExecuted() {
        return repository.readAll();
    }
    
    @Override
    public void reset() {
        List<Command> toRemove = repository.readAll();
        toRemove.forEach(command -> repository.remove(command.getId()));
    }
    
}
