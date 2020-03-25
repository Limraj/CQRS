/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.query.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.query.GetUserByIdQuery;
import com.devwider.scada.cqrs.handler.QueryHandlable;
import java.util.Optional;


/**
 *
 * @author Kamil-Tomasz
 */
class GetUserByIdQueryHandler extends AbstractUserQueryHandler implements QueryHandlable<Optional<User>, GetUserByIdQuery> {


    GetUserByIdQueryHandler(Repository<User> repository) {
        super(repository);
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery getUserQuery) {
        return Optional.ofNullable(getRepository().read(getUserQuery.getUserId()));
    }
}
