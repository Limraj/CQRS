/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.Event;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
public interface EventBus extends Bus<Event>, BusConsumerExecutable<Event>, EventsRevertable {
    List<Event> getEventsForCommand(Command command);
    <T extends Event> List<T> getEventsForType(Class<T> key);
    boolean isExecutedCommand(Command command);
}
