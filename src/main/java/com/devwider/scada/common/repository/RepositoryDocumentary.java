package com.devwider.scada.common.repository;


public interface RepositoryDocumentary<T extends UniqueDocumentary> {
    T read(String uuid);
    T remove(String uuid);
}
