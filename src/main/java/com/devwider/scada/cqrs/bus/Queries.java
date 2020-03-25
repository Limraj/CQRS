/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.Query;
import java.util.List;


/**
 *
 * @author Kamil-Tomasz
 */
enum Queries implements Repository<Query> {
    
    INSTANCE;
    
    private final Repository<Query> reposiotry = Repository.crud(Query.class);

    @Override
    public Query update(Query object) {
        return reposiotry.update(object);
    }

    @Override
    public Query read(long id) {
        return reposiotry.read(id);
    }

    @Override
    public Query create(Query object) {
        return reposiotry.create(object);
    }

    @Override
    public Query remove(long id) {
        return reposiotry.remove(id);
    }

    @Override
    public List<Query> readAll() {
        return reposiotry.readAll();
    }

    @Override
    public Query read(String uuid) {
        return reposiotry.read(uuid);
    }

    @Override
    public Query remove(String uuid) {
        return reposiotry.remove(uuid);
    }
}
