/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.command;

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
public class AddUserCommand extends CommandUnique implements Command {

    private final UUID userUuid;
    private final String username;
    private final String password;
    private final String email;
    private final String phone;
    private final boolean admin;
    private final boolean disabled;

}
