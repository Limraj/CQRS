package com.devwider.scada.cqrs.bus;

public class EventBusFactory {

    public static EventBus getInstance() {
        return EventBusSingleton.INSTANCE;
    }
}
