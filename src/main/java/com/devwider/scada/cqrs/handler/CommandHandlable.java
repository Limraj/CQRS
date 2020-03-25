/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.handler;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.Event;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Kamil-Tomasz
 * @param <C>
 */
public interface CommandHandlable<C extends Command> {
    CompletableFuture<Event> handle(C command);
}
