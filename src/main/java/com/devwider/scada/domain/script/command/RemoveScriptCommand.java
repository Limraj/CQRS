/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.command;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.CommandUnique;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 *
 * @author Kamil-Tomasz
 */
@ToString(callSuper = true)
@Getter
@EqualsAndHashCode(callSuper = false)
public class RemoveScriptCommand extends CommandUnique implements Command {

    private final UUID scriptUuid;

    public RemoveScriptCommand(UUID scriptUuid) {
        this.scriptUuid = scriptUuid;
    }
}
