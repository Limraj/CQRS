/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.event;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.EventUnique;
import com.devwider.scada.cqrs.Event;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.command.AddUserCommand;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 *
 * @author Kamil-Tomasz
 */
@ToString(callSuper = true)
@Getter
@EqualsAndHashCode(callSuper = false)
public class RemovedUserEvent extends EventUnique implements Event {
    
    private final User user;
    private final UUID commandUuid;

    public RemovedUserEvent(User user, UUID commandUuid) {
        this.user = user;
        this.commandUuid = commandUuid;
    }

    @Override
    public Command toRevertCommand() {
        
        return AddUserCommand.builder()
                .admin(user.isAdmin())
                .disabled(user.isDisabled())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .username(user.getUsername())
                .userUuid(user.getUuid())
                .build();
    }

}
