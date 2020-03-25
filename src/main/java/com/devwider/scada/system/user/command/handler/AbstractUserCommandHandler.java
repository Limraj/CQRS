package com.devwider.scada.system.user.command.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.system.user.User;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PROTECTED)
abstract class AbstractUserCommandHandler {

    private final Repository<User> reposiotry;

    AbstractUserCommandHandler(Repository<User> reposiotry) {
        this.reposiotry = reposiotry;
    }
}
