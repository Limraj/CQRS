/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs.bus;

import java.util.List;

/**
 *
 * @author Kamil-Tomasz
 * @param <T>
 */
interface Bus<T> {
    List<T> readExecuted();
    void reset();
}
