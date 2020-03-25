/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.query;


import com.devwider.scada.cqrs.bus.CommandBus;
import com.devwider.scada.cqrs.bus.CommandBusFactory;
import com.devwider.scada.cqrs.bus.QueryBus;
import com.devwider.scada.cqrs.bus.QueryBusFactory;
import com.devwider.scada.domain.TestUtil;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.command.AddScriptCommand;

import java.util.Optional;
import java.util.UUID;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kamil-Tomasz
 */
public class GetScriptByXidQueryTest {

    private final QueryBus queryBus = QueryBusFactory.getInstance();
    
    @AfterClass
    public static void after() {
        TestUtil.resetBuses(GetScriptByXidQueryTest.class);
    }

    @BeforeClass
    public static void before() {
        TestUtil.resetBuses(GetScriptByXidQueryTest.class);
        QueryBusFactory.getInstance().reset();
        AddScriptCommand addScriptCommand = AddScriptCommand.builder()
                .name("nameScript")
                .script("return 1 + 2")
                .userUuid(UUID.randomUUID())
                .xid("SF_DG4434")
                .build();
        AddScriptCommand addScriptCommand2 = AddScriptCommand.builder()
                .name("nameScript2")
                .script("return 1 + 2 + 3")
                .userUuid(UUID.randomUUID())
                .xid("SF_DG4433")
                .build();
        CommandBus commandBus = CommandBusFactory.getInstance();
        commandBus.execute(addScriptCommand);
        commandBus.execute(addScriptCommand2);
    }

    @Test
    public void test_invoke_execute_for_get_script_by_xid_query_then_script_with_xid() {
        
        //given:
        String xid = "SF_DG4434";
        GetScriptByXidQuery getScriptByXidQuery = new GetScriptByXidQuery(xid);
        
        //when:
        Optional<Script> scriptOpt = queryBus.execute(getScriptByXidQuery);
        
        //then:
        assertNotEquals(Optional.empty(), scriptOpt);
        Script script = scriptOpt.get();
        assertEquals(xid, script.getXid());

    }
    
}
