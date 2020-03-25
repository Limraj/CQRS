/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.query.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.query.GetUserByUsernameQuery;
import java.util.List;
import java.util.stream.Collectors;
import com.devwider.scada.cqrs.handler.QueryHandlable;

/**
 *
 * @author Kamil-Tomasz
 */
class GetUserByUsernameQueryHandler extends AbstractUserQueryHandler implements QueryHandlable<List<User>, GetUserByUsernameQuery> {

    GetUserByUsernameQueryHandler(Repository<User> repository) {
        super(repository);
    }

    @Override
    public List<User> handle(GetUserByUsernameQuery getUserByNameQuery) {
        return getRepository().readAll().stream()
                .filter(a -> a.getUsername().equalsIgnoreCase(getUserByNameQuery.getUsername()))
                .collect(Collectors.toList());
    }
    
}
