/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.common.repository;

/**
 *
 * @author Kamil-Tomasz
 * @param <T>
 */
public interface RepositoryRelational<T extends UniqueRelational> {
    T read(long id);
    T remove(long id);
}
