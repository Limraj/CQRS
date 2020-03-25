/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import com.devwider.scada.cqrs.Event;
import com.devwider.scada.system.user.event.AddedUserEvent;
import com.devwider.scada.cqrs.FailedCommandEvent;
import com.devwider.scada.system.user.event.RemovedUserEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.devwider.scada.cqrs.handler.EventHandlable;
import com.devwider.scada.domain.script.event.AddedScriptEvent;
import com.devwider.scada.domain.script.event.RemovedScriptEvent;
import com.devwider.scada.domain.script.event.handler.ScriptEventHandlerFactory;
import com.devwider.scada.system.user.event.handler.UserEventHandlerFactory;

/**
 *
 * @author Kamil-Tomasz
 */
class EventHandlerProvider {
    
    private static final Map<Class<? extends Event>, EventHandlable<? extends Event>> HANDLERS = new HashMap<>();
    
    static {
        HANDLERS.put(AddedUserEvent.class, 
                UserEventHandlerFactory.addedUserEventHandler());
        HANDLERS.put(RemovedUserEvent.class, 
                UserEventHandlerFactory.removedUserEventHandler());
        HANDLERS.put(AddedScriptEvent.class, 
                ScriptEventHandlerFactory.addedScriptEventHandler());
        HANDLERS.put(RemovedScriptEvent.class, 
                ScriptEventHandlerFactory.removedScriptEventHandler());
        HANDLERS.put(FailedCommandEvent.class, FailedCommandEventHandler.INSTANCE);
    }
    
    public static EventHandlable<Event> handler(Class<? extends Event> key) {
        if(!HANDLERS.containsKey(key))
            throw new IllegalArgumentException("No registered handler for the event: " + key.getName());        
        return (EventHandlable<Event>) HANDLERS.get(key);
    }
}
