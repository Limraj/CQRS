/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.query.handler;

import com.devwider.scada.cqrs.Query;
import com.devwider.scada.cqrs.handler.QueryHandlable;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.query.GetUserByIdQuery;
import com.devwider.scada.system.user.query.GetUserByUsernameQuery;
import com.devwider.scada.system.user.query.GetUserByUuidQuery;
import com.devwider.scada.system.user.query.repository.UserQueryRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Kamil-Tomasz
 */
public class UserQueryHandlerFactory {
    
    public static QueryHandlable<?, ? extends Query> getUserByIdQueryHandler() {
        return new GetUserByIdQueryHandler(UserQueryRepository.INSTANCE);
    }
    
    public static QueryHandlable<?, ? extends Query> getUserByUsernameQueryHandler() {
        return new GetUserByUsernameQueryHandler(UserQueryRepository.INSTANCE);
    }

    public static QueryHandlable<?, ? extends Query> getUserByUuidQueryHandler() {
        return new GetUserByUuidQueryHandler(UserQueryRepository.INSTANCE);
    }
}
