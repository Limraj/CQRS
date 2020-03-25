/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.command.repository;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.system.user.User;
import java.util.List;
import com.devwider.scada.common.repository.RepositoryRelational;

/**
 *
 * @author Kamil-Tomasz
 */
public enum UserCommandRepository implements Repository<User> {
    
    INSTANCE;
    
    private final Repository<User> repository = Repository.crud(User.class);

    @Override
    public User update(User object) {
        return repository.update(object);
    }

    @Override
    public User read(long id) {
        return repository.read(id);
    }

    @Override
    public User create(User object) {
        return repository.create(object);
    }

    @Override
    public User remove(long id) {
        return repository.remove(id);
    }

    @Override
    public List<User> readAll() {
        return repository.readAll();
    }

    @Override
    public User read(String uuid) {
        return repository.read(uuid);
    }

    @Override
    public User remove(String uuid) {
        return repository.remove(uuid);
    }
}
