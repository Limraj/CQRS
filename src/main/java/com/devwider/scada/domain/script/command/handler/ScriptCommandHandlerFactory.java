/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.command.handler;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.domain.script.command.respository.ScriptCommandReposiotory;
import com.devwider.scada.cqrs.handler.CommandHandlable;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Kamil-Tomasz
 */
public class ScriptCommandHandlerFactory {
    
    public static CommandHandlable<? extends Command> addScriptCommandHandler() {
        return new AddScriptCommandHandler(ScriptCommandReposiotory.INSTANCE);
    }
    
    public static CommandHandlable<? extends Command> removeScriptCommandHandler() {
        return new RemoveScriptCommandHandler(ScriptCommandReposiotory.INSTANCE);
    }
}
