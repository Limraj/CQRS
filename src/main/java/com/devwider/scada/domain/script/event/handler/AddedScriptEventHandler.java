/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.event.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.common.repository.RepositoryRelational;
import com.devwider.scada.cqrs.handler.EventHandlable;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.event.AddedScriptEvent;

/**
 *
 * @author Kamil-Tomasz
 */
class AddedScriptEventHandler extends AbstractScriptEventHandler implements EventHandlable<AddedScriptEvent> {

    public AddedScriptEventHandler(Repository<Script> repository) {
        super(repository);
    }

    @Override
    public void handle(AddedScriptEvent event) {
        getRepository().create(event.getScript());
    }
    
}
