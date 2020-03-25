package com.devwider.scada.domain.script.event.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.domain.script.Script;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
abstract class AbstractScriptEventHandler {
    private final Repository<Script> repository;

    public AbstractScriptEventHandler(Repository<Script> repository) {
        this.repository = repository;
    }
}
