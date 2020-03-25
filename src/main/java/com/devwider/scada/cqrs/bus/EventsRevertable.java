package com.devwider.scada.cqrs.bus;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.Event;

public interface EventsRevertable {
    void revertChangesAll();
    void revertChangesForCommand(Command command);
    <T extends Event> void revertChangesForEventType(Class<T> key);
}
