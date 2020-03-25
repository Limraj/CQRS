package com.devwider.scada.cqrs.bus;

public class QueryBusFactory {
    public static QueryBus getInstance() {
        return QueryBusSingleton.INSTANCE;
    }
}
