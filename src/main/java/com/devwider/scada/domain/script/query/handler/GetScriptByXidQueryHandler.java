/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.query.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.common.repository.RepositoryRelational;
import com.devwider.scada.cqrs.handler.QueryHandlable;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.query.GetScriptByXidQuery;
import java.util.Optional;

/**
 *
 * @author Kamil-Tomasz
 */

class GetScriptByXidQueryHandler extends AbstractScriptQueryHandler
        implements QueryHandlable<Optional<Script>, GetScriptByXidQuery> {


    GetScriptByXidQueryHandler(Repository<Script> repository) {
        super(repository);
    }

    @Override
    public Optional<Script> handle(GetScriptByXidQuery query) {
        return getRepository().readAll().stream()
                .filter(a -> {
                    String xid = a.getXid();
                    String xid2 = query.getXid();
                    return xid.equalsIgnoreCase(xid2);
                })
                .findFirst();
    }
    
}
