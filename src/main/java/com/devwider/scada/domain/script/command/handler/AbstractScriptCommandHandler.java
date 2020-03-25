package com.devwider.scada.domain.script.command.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.domain.script.Script;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
abstract class AbstractScriptCommandHandler {

    private final Repository<Script> reposiotry;

    AbstractScriptCommandHandler(Repository<Script> reposiotry) {
        this.reposiotry = reposiotry;
    }
}
