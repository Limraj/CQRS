package com.devwider.scada.common.repository;

import java.util.List;

public interface Repository<T extends Unique> extends RepositoryDocumentary<T>, RepositoryRelational<T> {

    T update(T object);
    T create(T object);
    List<T> readAll();

    static <T extends Unique> Repository<T> crud(Class<T> clazz) {
        return new RepositoryImpl<>();
    }
}
