/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.bus.CommandBus;
import com.devwider.scada.cqrs.bus.CommandBusFactory;
import com.devwider.scada.cqrs.bus.EventBus;
import com.devwider.scada.cqrs.bus.EventBusFactory;
import com.devwider.scada.cqrs.bus.QueryBus;
import com.devwider.scada.cqrs.bus.QueryBusFactory;
import com.devwider.scada.domain.script.command.AddScriptCommand;
import com.devwider.scada.domain.script.event.AddedScriptEvent;

import java.util.UUID;

/**
 *
 * @author Kamil-Tomasz
 */
public class TestUtil {
    
    public static void resetBuses(Class<?> test) {
        System.out.println("____________________for_" + test);
        CommandBus commandBus = CommandBusFactory.getInstance();
        EventBus eventBus = EventBusFactory.getInstance();
        QueryBus queryBus = QueryBusFactory.getInstance();
        commandBus.readExecuted().stream().forEachOrdered(System.out::println);
        eventBus.readExecuted().stream().forEachOrdered(System.out::println);
        queryBus.readExecuted().stream().forEachOrdered(System.out::println);
        System.out.println("____________________---_");
        eventBus.revertChangesAll();
        commandBus.readExecuted().stream().forEachOrdered(System.out::println);
        eventBus.readExecuted().stream().forEachOrdered(System.out::println);
        queryBus.readExecuted().stream().forEachOrdered(System.out::println);
        commandBus.reset();
        eventBus.reset();
        queryBus.reset();
        System.out.println("____________________END_");
    }
}
