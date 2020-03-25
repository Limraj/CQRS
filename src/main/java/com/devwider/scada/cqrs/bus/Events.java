/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.Event;

import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 */
enum Events implements Repository<Event> {
    
    INSTANCE;
    
    private final Repository<Event> reposiotry = Repository.crud(Event.class);

    @Override
    public Event update(Event object) {
        return reposiotry.update(object);
    }

    @Override
    public Event read(long id) {
        return reposiotry.read(id);
    }

    @Override
    public Event create(Event object) {
        return reposiotry.create(object);
    }

    @Override
    public Event remove(long id) {
        return reposiotry.remove(id);
    }

    @Override
    public List<Event> readAll() {
        return reposiotry.readAll();
    }

    @Override
    public Event read(String uuid) {
        return reposiotry.read(uuid);
    }

    @Override
    public Event remove(String uuid) {
        return reposiotry.remove(uuid);
    }
}
