package com.devwider.scada.cqrs.bus;

import com.devwider.scada.cqrs.FailedCommandEvent;
import com.devwider.scada.cqrs.handler.EventHandlable;


enum FailedCommandEventHandler implements EventHandlable<FailedCommandEvent> {

    INSTANCE;

    @Override
    public void handle(FailedCommandEvent event) {

    }
}
