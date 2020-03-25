package com.devwider.scada.system.user.query.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.handler.QueryHandlable;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.query.GetUserByUuidQuery;

import java.util.Optional;

class GetUserByUuidQueryHandler extends AbstractUserQueryHandler implements QueryHandlable<Optional<User>, GetUserByUuidQuery> {

    GetUserByUuidQueryHandler(Repository<User> repository) {
        super(repository);
    }

    @Override
    public Optional<User> handle(GetUserByUuidQuery query) {
        return getRepository()
                .readAll()
                .stream()
                .filter(a -> a.getUuid().equals(query.getUuid()))
                .findAny();
    }
}
