package com.devwider.scada.domain.script.query.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.domain.script.Script;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
abstract class AbstractScriptQueryHandler {

    private final Repository<Script> repository;

    public AbstractScriptQueryHandler(Repository<Script> repository) {
        this.repository = repository;
    }
}
