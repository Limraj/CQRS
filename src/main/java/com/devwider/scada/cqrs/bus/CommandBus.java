package com.devwider.scada.cqrs.bus;

import com.devwider.scada.cqrs.Command;

public interface CommandBus extends Bus<Command>, BusConsumerExecutable<Command> {
}
