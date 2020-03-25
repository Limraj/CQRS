/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.domain.script.command;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.CommandUnique;
import com.devwider.scada.cqrs.bus.CommandBus;
import com.devwider.scada.cqrs.bus.CommandBusFactory;
import com.devwider.scada.cqrs.bus.QueryBus;
import com.devwider.scada.cqrs.bus.QueryBusFactory;
import com.devwider.scada.domain.TestUtil;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.command.respository.ScriptCommandReposiotory;
import com.devwider.scada.domain.script.query.GetScriptByXidQuery;
import com.devwider.scada.domain.script.query.repository.ScriptQueryRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 *
 * @author Kamil-Tomasz
 */
public class AddScriptCommandTest {

    private final QueryBus queryBus = QueryBusFactory.getInstance();
    private final CommandBus commandBus = CommandBusFactory.getInstance();
    
    @BeforeClass
    public static void before() {
        TestUtil.resetBuses(AddScriptCommandTest.class);
    }
    
    @AfterClass
    public static void after() {
        TestUtil.resetBuses(AddScriptCommandTest.class);
    }
    
    @Test
    public void test_invoke_execute_for_add_script_command_then_created_script() {

        //given:
        CommandUnique command = ScriptCommand.Add.builder().build();

        AddScriptCommand addScriptCommand = AddScriptCommand.builder()
                .name("nameScript")
                .script("return 1 + 2")
                .userUuid(UUID.randomUUID())
                .scriptUuid(UUID.randomUUID())
                .xid("SF_DG4434")
                .build();

        GetScriptByXidQuery getUserByXidQuery = new GetScriptByXidQuery("SF_DG4434");

        //when:
        Repository<Script> scriptsCommand = ScriptCommandReposiotory.INSTANCE;
        int sizeCommand = scriptsCommand.readAll().size();

        //then:
        assertEquals(0, sizeCommand);

        //when:
        Repository<Script> scriptsQuery = ScriptQueryRepository.INSTANCE;
        int sizeQuery = scriptsQuery.readAll().size();

        //then:
        assertEquals(0, sizeQuery);
        
        //when:
        Optional<Script> userOpt = queryBus.execute(getUserByXidQuery);
        //then:
        assertEquals(Optional.empty(), userOpt);
        
        //when:
        commandBus.execute(addScriptCommand);
        
        //and:
        userOpt = queryBus.execute(getUserByXidQuery);
        
        //then:
        assertNotEquals(Optional.empty(), userOpt);
        Script script = userOpt.get();
        assertEquals(addScriptCommand.getName(), script.getName());
        assertEquals(addScriptCommand.getUserUuid(), script.getUserUuid());
        assertEquals(addScriptCommand.getXid(), script.getXid());
        assertEquals(addScriptCommand.getScript(), script.getScript());
        assertEquals(addScriptCommand.getScriptUuid(), script.getUuid());
    }
    
}
