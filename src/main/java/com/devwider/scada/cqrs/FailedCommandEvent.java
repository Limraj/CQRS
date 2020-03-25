package com.devwider.scada.cqrs;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class FailedCommandEvent extends EventUnique implements Event {

    private final Command command;

    public FailedCommandEvent(Command command) {
        this.command = command;
    }

    @Override
    public Command toRevertCommand() {
        return FailedCommand.INSTANCE;
    }

    @Override
    public UUID getCommandUuid() {
        return command.getUuid();
    }
}
