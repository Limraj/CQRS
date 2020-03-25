/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.query.handler;

import com.devwider.scada.cqrs.Query;
import com.devwider.scada.cqrs.handler.QueryHandlable;
import com.devwider.scada.domain.script.query.repository.ScriptQueryRepository;

/**
 *
 * @author Kamil-Tomasz
 */
public class ScriptQueryHandlerFactory {
    public static QueryHandlable<?, ? extends Query> getScriptByXidQueryHandler() {
        return new GetScriptByXidQueryHandler(ScriptQueryRepository.INSTANCE);
    }
    public static QueryHandlable<?, ? extends Query> getScriptByUuidQueryHandler() {
        return new GetScriptByUuidQueryHandler(ScriptQueryRepository.INSTANCE);
    }
}
