/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.command;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.CommandUnique;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 *
 * @author Kamil-Tomasz
 */
@Builder
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class AddScriptCommand extends CommandUnique implements Command {

    private final String xid;
    private final String name;
    private final String script;
    private UUID scriptUuid;
    private final UUID userUuid;

}
