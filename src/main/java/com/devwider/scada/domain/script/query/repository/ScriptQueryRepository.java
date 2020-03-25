/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.query.repository;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.common.repository.RepositoryRelational;
import com.devwider.scada.domain.script.Script;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
public enum ScriptQueryRepository implements Repository<Script> {

    INSTANCE;
    
    private final Repository<Script> repository = Repository.crud(Script.class);

    @Override
    public Script update(Script object) {
        return repository.update(object);
    }

    @Override
    public Script read(long id) {
        return repository.read(id);
    }

    @Override
    public Script create(Script object) {
        return repository.create(object);
    }

    @Override
    public Script remove(long id) {
        return repository.remove(id);
    }

    @Override
    public List<Script> readAll() {
        return repository.readAll();
    }

    @Override
    public Script read(String uuid) {
        return repository.read(uuid);
    }

    @Override
    public Script remove(String uuid) {
        return repository.remove(uuid);
    }
}
