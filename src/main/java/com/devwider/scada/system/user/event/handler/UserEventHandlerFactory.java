/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.event.handler;

import com.devwider.scada.cqrs.handler.EventHandlable;
import com.devwider.scada.system.user.query.repository.UserQueryRepository;

/**
 *
 * @author Kamil-Tomasz
 */
public class UserEventHandlerFactory {
    
    public static EventHandlable<?> addedUserEventHandler() {
        return new AddedUserEventHandler(UserQueryRepository.INSTANCE);
    }
    
    public static EventHandlable<?> removedUserEventHandler() {
        return new RemovedUserEventHandler(UserQueryRepository.INSTANCE);
    }
}
