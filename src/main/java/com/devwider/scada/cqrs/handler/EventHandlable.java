/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.handler;

import com.devwider.scada.cqrs.Event;

/**
 *
 * @author Kamil-Tomasz
 * @param <E>
 */
public interface EventHandlable<E extends Event> {
    void handle(E event);
}
