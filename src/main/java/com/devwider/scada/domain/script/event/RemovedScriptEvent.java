/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.event;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.Event;
import com.devwider.scada.cqrs.EventUnique;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.command.AddScriptCommand;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 *
 * @author Kamil-Tomasz
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class RemovedScriptEvent extends EventUnique implements Event {
    
    private final Script script;
    private final UUID commandUuid;

    public RemovedScriptEvent(Script script, UUID commandUuid) {
        this.script = script;
        this.commandUuid = commandUuid;
    }

    @Override
    public Command toRevertCommand() {
        return AddScriptCommand.builder()
                .name(script.getName())
                .script(script.getScript())
                .scriptUuid(script.getUuid())
                .xid(script.getXid())
                .build();
              
    }
    
    
}
