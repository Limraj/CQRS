/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.query;

import com.devwider.scada.cqrs.Query;
import com.devwider.scada.cqrs.QueryUnique;
import com.devwider.scada.domain.script.Script;
import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Kamil-Tomasz
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GetScriptByXidQuery extends QueryUnique implements Query<Optional<Script>> {
    
    private final String xid;

    public GetScriptByXidQuery(String xid) {
        this.xid = xid;
    }

}
