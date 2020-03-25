/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.common.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Kamil-Tomasz
 * @param <T>
 */
class RepositoryImpl<T extends Unique> implements Repository<T> {

    private final Map<Long, T> objects = new ConcurrentSkipListMap<>();
    private final static AtomicLong SERIAL = new AtomicLong();


    @Override
    public T read(long id) {
        return objects.get(id);
    }

    @Override
    public T read(String uuid) {
        return objects.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(entry -> entry.getUuid().equals(UUID.fromString(uuid)))
                .findFirst()
                .orElse(null);
    }
        
    @Override
    public T create(T object) {
        object.setId(SERIAL.getAndIncrement());
        return update(object);
    }
    
    @Override
    public List<T> readAll() {
        return new ArrayList<>(objects.values());
    }

    @Override
    public T update(T object) {
        objects.put(object.getId(), object);
        return objects.get(object.getId());
    }

    @Override
    public T remove(long id) {
        T object = objects.get(id);
        objects.remove(id);
        return object;
    }

    @Override
    public T remove(String uuid) {
        T object = read(uuid);
        objects.entrySet().removeIf(entry -> entry.getValue().getUuid().equals(UUID.fromString(uuid)));
        return object;
    }
}
