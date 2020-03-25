/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.command.handler;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.system.user.command.repository.UserCommandRepository;
import com.devwider.scada.cqrs.handler.CommandHandlable;

/**
 *
 * @author Kamil-Tomasz
 */
public class UserCommandHandlerFactory {
    
    public static CommandHandlable<? extends Command> addUserCommandHandler() {
        return new AddUserCommandHandler(UserCommandRepository.INSTANCE);
    }
    
    public static CommandHandlable<? extends Command> removeUserCommandHandler() {
        return new RemoveUserCommandHandler(UserCommandRepository.INSTANCE);
    }
}
