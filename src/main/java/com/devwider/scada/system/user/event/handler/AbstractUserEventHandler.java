package com.devwider.scada.system.user.event.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.system.user.User;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
abstract class AbstractUserEventHandler {

    private final Repository<User> repository;

    public AbstractUserEventHandler(Repository<User> repository) {
        this.repository = repository;
    }
}
