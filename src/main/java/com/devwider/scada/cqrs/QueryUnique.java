/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.cqrs;

import com.devwider.scada.common.repository.Unique;
import com.devwider.scada.common.repository.UniqueDocumentary;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Kamil-Tomasz
 */
@Getter
@ToString
public abstract class QueryUnique {

    private UUID uuid = UUID.randomUUID();
    @Setter
    private long id;
}
