package com.devwider.scada.cqrs.bus;


public class CommandBusFactory {

    public static CommandBus getInstance() {
        return CommandBusSingleton.INSTANCE;
    }
}
