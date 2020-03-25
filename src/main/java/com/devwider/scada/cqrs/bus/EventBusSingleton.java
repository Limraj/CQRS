/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.Event;

import java.util.Collections;
import java.util.List;
import com.devwider.scada.cqrs.handler.EventHandlable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Kamil-Tomasz
 */

enum EventBusSingleton implements EventBus {
    
    INSTANCE;
    
    private final Repository<Event> repository = Events.INSTANCE;

    @Override
    public void execute(Event event) {
        Event eventCreated = repository.create(event);
        EventHandlable<Event> handler = EventHandlerProvider.handler(eventCreated.getClass());
        handler.handle(eventCreated);
    }

    @Override
    public void execute(Event event, Event... events) {
        execute(event);
        Stream.of(events).forEach(this::execute);
    }

    @Override
    public List<Event> readExecuted() {
        return repository.readAll();
    }
    
    @Override
    public void reset() {
        List<Event> toRemove = repository.readAll();
        toRemove.stream().forEach(a -> repository.remove(a.getId()));
    }

    @Override
    public List<Event> getEventsForCommand(Command command) {
        return repository.readAll().stream()
                .filter(event -> event.getCommandUuid().equals(command.getUuid()))
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean isExecutedCommand(Command command) {
        return repository.readAll().stream()
                .anyMatch(event -> event.getCommandUuid().equals(command.getUuid()));
    }
    
    @Override
    public <T extends Event> List<T> getEventsForType(Class<T> key) {
        return repository.readAll().stream()
                .filter(event -> event.getClass().equals(key))
                .collect(() -> new ArrayList<>(),
                        (a, b) -> a.add((T)b) , List::addAll);
    }

    @Override
    public void revertChangesAll() {
        List<Event> snapshot = new ArrayList<>(repository.readAll());
        revert(snapshot);
    }

    @Override
    public void revertChangesForCommand(Command command) {
        List<Event> snapshot = getEventsForCommand(command);
        revert(snapshot);
    }

    @Override
    public <T extends Event> void revertChangesForEventType(Class<T> key) {
        List<T> snapshot = getEventsForType(key);
        revert(snapshot);
    }

    private <T extends Event> void revert(List<T> snapshot) {
        Collections.reverse(snapshot);
        CommandBus commandBus = CommandBusFactory.getInstance();
        snapshot.forEach(event -> commandBus.execute(event.toRevertCommand()));
    }
}
