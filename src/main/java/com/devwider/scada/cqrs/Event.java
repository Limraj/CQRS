/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs;

import com.devwider.scada.common.repository.Unique;

import java.util.UUID;

/**
 *
 * @author Kamil-Tomasz
 */
public interface Event extends Unique {
    
    Command toRevertCommand();
    UUID getCommandUuid();
}
