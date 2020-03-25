/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script;

import com.devwider.scada.common.repository.Unique;
import com.devwider.scada.common.repository.UniqueDocumentary;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.devwider.scada.common.repository.UniqueRelational;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Kamil-Tomasz
 */
@Getter
@ToString
public class Script implements Unique {

    @Setter
    private long id;
    private UUID uuid;
    private String xid;
    private String name;
    private String script;
    private UUID userUuid;

    @Builder
    private Script(long id, UUID uuid, String xid, String name, String script, UUID userUuid) {
        this.id = id;
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.xid = xid;
        this.name = name;
        this.script = script;
        this.userUuid = userUuid;
    }
}
