/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.event.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.handler.EventHandlable;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.event.RemovedScriptEvent;

import java.util.UUID;

/**
 *
 * @author Kamil-Tomasz
 */
class RemovedScriptEventHandler extends AbstractScriptEventHandler implements EventHandlable<RemovedScriptEvent> {

    public RemovedScriptEventHandler(Repository<Script> repository) {
        super(repository);
    }

    @Override
    public void handle(RemovedScriptEvent event) {
        Script script = event.getScript();
        UUID uuid = script.getUuid();
        if(uuid==null)
            return;
        getRepository().remove(uuid.toString());
    }
    
}
