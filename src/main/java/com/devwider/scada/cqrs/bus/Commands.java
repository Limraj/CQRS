/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.common.repository.RepositoryRelational;
import com.devwider.scada.cqrs.Command;
import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
enum Commands implements Repository<Command> {

    INSTANCE;

    private final Repository<Command> repository = Repository.crud(Command.class);

    @Override
    public Command update(Command object) {
        return repository.update(object);
    }

    @Override
    public Command read(long id) {
        return repository.read(id);
    }

    @Override
    public Command create(Command object) {
        return repository.create(object);
    }

    @Override
    public Command remove(long id) {
        return repository.remove(id);
    }

    @Override
    public List<Command> readAll() {
        return repository.readAll();
    }

    @Override
    public Command read(String uuid) {
        return repository.read(uuid);
    }

    @Override
    public Command remove(String uuid) {
        return repository.remove(uuid);
    }
}
