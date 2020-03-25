package com.devwider.scada.system.user.query.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.system.user.User;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
abstract class AbstractUserQueryHandler {

    private final Repository<User> repository;

    public AbstractUserQueryHandler(Repository<User> repository) {
        this.repository = repository;
    }

}
