/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.system.user.command.AddUserCommand;
import com.devwider.scada.system.user.command.RemoveUserCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.devwider.scada.domain.script.command.AddScriptCommand;
import com.devwider.scada.domain.script.command.RemoveScriptCommand;
import com.devwider.scada.domain.script.command.handler.ScriptCommandHandlerFactory;
import com.devwider.scada.system.user.command.handler.UserCommandHandlerFactory;
import com.devwider.scada.cqrs.handler.CommandHandlable;

/**
 *
 * @author Kamil-Tomasz
 */
class CommandHandlerProvider {

    private static final Map<Class<? extends Command>, CommandHandlable<? extends Command>> HANDLERS = new HashMap<>();
    
    static {
        HANDLERS.put(AddUserCommand.class, 
                UserCommandHandlerFactory.addUserCommandHandler());
        HANDLERS.put(RemoveUserCommand.class, 
                UserCommandHandlerFactory.removeUserCommandHandler());
        HANDLERS.put(AddScriptCommand.class, 
                ScriptCommandHandlerFactory.addScriptCommandHandler());
        HANDLERS.put(RemoveScriptCommand.class, 
                ScriptCommandHandlerFactory.removeScriptCommandHandler());
    }
    
    public static CommandHandlable<Command> handler(Class<? extends Command> key) {
        if(!HANDLERS.containsKey(key))
            throw new IllegalArgumentException("No registered handler for the command: " + key.getName());
        return (CommandHandlable<Command>) HANDLERS.get(key);
    }
}
