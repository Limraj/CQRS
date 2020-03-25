/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.Query;
import java.util.List;

import com.devwider.scada.cqrs.handler.QueryHandlable;

/**
 *
 * @author Kamil-Tomasz
 */
enum QueryBusSingleton implements QueryBus {
    
    INSTANCE;
    
    private final Repository<Query> queries = Queries.INSTANCE;

    @Override
    public <R> R execute(Query<R> query) {
        Query<R> queryCreated = queries.create(query);
        QueryHandlable<R, Query<R>> handler = QueryHandlerProvider.handler(queryCreated.getClass());
        return handler.handle(queryCreated);
    }

    @Override
    public List<Query> readExecuted() {
        return queries.readAll();
    }

    @Override
    public void reset() {
        List<Query> toRemove = queries.readAll();
        toRemove.stream().forEach(a -> queries.remove(a.getId()));
    }
}
