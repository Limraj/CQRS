/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.event.handler;

import com.devwider.scada.cqrs.Event;
import com.devwider.scada.cqrs.handler.EventHandlable;
import com.devwider.scada.domain.script.command.respository.ScriptCommandReposiotory;
import com.devwider.scada.domain.script.query.repository.ScriptQueryRepository;

/**
 *
 * @author Kamil-Tomasz
 */
public class ScriptEventHandlerFactory {
    
    public static EventHandlable<? extends Event> addedScriptEventHandler() {
        return new AddedScriptEventHandler(ScriptQueryRepository.INSTANCE);
    }
    
    public static EventHandlable<? extends Event> removedScriptEventHandler() {
        return new RemovedScriptEventHandler(ScriptQueryRepository.INSTANCE);
    }
}
