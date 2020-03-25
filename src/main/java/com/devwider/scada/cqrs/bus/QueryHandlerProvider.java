/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import com.devwider.scada.cqrs.Query;
import com.devwider.scada.cqrs.handler.QueryHandlable;
import com.devwider.scada.domain.script.query.GetScriptByUuidQuery;
import com.devwider.scada.domain.script.query.GetScriptByXidQuery;
import com.devwider.scada.domain.script.query.handler.ScriptQueryHandlerFactory;
import com.devwider.scada.system.user.query.GetUserByIdQuery;
import com.devwider.scada.system.user.query.GetUserByUsernameQuery;
import com.devwider.scada.system.user.query.GetUserByUuidQuery;
import com.devwider.scada.system.user.query.handler.UserQueryHandlerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kamil-Tomasz
 */
class QueryHandlerProvider {
    
    private static final Map<Class<? extends Query>, QueryHandlable<?, ? extends Query>> HANDLERS = new HashMap<>();
    
    static {
        HANDLERS.put(GetUserByIdQuery.class, 
                UserQueryHandlerFactory.getUserByIdQueryHandler());
        HANDLERS.put(GetUserByUsernameQuery.class, 
                UserQueryHandlerFactory.getUserByUsernameQueryHandler());
        HANDLERS.put(GetUserByUuidQuery.class,
                UserQueryHandlerFactory.getUserByUuidQueryHandler());
        HANDLERS.put(GetScriptByXidQuery.class, 
                ScriptQueryHandlerFactory.getScriptByXidQueryHandler());
        HANDLERS.put(GetScriptByUuidQuery.class,
                ScriptQueryHandlerFactory.getScriptByUuidQueryHandler());
    }
    
    public static <R> QueryHandlable<R, Query<R>> handler(Class<? extends Query> key) {
        if(!HANDLERS.containsKey(key))
            throw new IllegalArgumentException("No registered handler for the query: " + key.getName());
        return (QueryHandlable<R, Query<R>>) HANDLERS.get(key);
    }
}
