package com.devwider.scada.domain.script.command;

import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.CommandUnique;
import lombok.*;

import java.util.UUID;

public class ScriptCommand {

    @Builder
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = false)
    public static class Add extends CommandUnique implements Command {

        private final String xid;
        private final String name;
        private final String script;
        private UUID scriptUuid;
        private final UUID userUuid;

    }

    @ToString(callSuper = true)
    @Getter
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    public class Remove extends CommandUnique implements Command {
        private final UUID scriptUuid;
    }
}
