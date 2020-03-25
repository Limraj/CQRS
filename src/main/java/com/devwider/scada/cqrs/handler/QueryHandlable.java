/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.handler;

import com.devwider.scada.cqrs.Query;

/**
 *
 * @author Kamil-Tomasz
 * @param <R>
 * @param <Q>
 */
public interface QueryHandlable<R, Q extends Query<R>> {
    R handle(Q query);
}
