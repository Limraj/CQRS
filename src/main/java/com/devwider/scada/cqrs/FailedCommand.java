package com.devwider.scada.cqrs;

import com.devwider.scada.common.Common;
import lombok.ToString;

import java.util.UUID;

@ToString(callSuper = true)
public enum FailedCommand implements Command {

    INSTANCE;

    @Override
    public UUID getUuid() {
        return UUID.fromString("");
    }

    @Override
    public long getId() {
        return Common.NEW_ID;
    }

    @Override
    public void setId(long id) {

    }
}
