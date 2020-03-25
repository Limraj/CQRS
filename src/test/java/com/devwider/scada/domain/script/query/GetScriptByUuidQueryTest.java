package com.devwider.scada.domain.script.query;

import com.devwider.scada.cqrs.bus.CommandBus;
import com.devwider.scada.cqrs.bus.CommandBusFactory;
import com.devwider.scada.cqrs.bus.QueryBus;
import com.devwider.scada.cqrs.bus.QueryBusFactory;
import com.devwider.scada.domain.TestUtil;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.command.AddScriptCommand;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class GetScriptByUuidQueryTest {

    private final QueryBus queryBus = QueryBusFactory.getInstance();
    private final static UUID uuid = UUID.randomUUID();
    private final static String xid = "xid_test";

    @AfterClass
    public static void after() {
        TestUtil.resetBuses(GetScriptByUuidQueryTest.class);
    }

    @BeforeClass
    public static void before() {
        TestUtil.resetBuses(GetScriptByUuidQueryTest.class);
        AddScriptCommand addScriptCommand = AddScriptCommand.builder()
                .name("nameScript")
                .script("return 1 + 2")
                .userUuid(UUID.randomUUID())
                .xid(xid)
                .scriptUuid(uuid)
                .build();
        AddScriptCommand addScriptCommand2 = AddScriptCommand.builder()
                .name("nameScript")
                .script("return 1 + 2")
                .userUuid(UUID.randomUUID())
                .xid("SF_DG4433")
                .build();
        CommandBus commandBus = CommandBusFactory.getInstance();
        commandBus.execute(addScriptCommand);
        commandBus.execute(addScriptCommand2);
    }

    @Test
    public void test_invoke_execute_for_get_script_by_uuid_query_then_script_with_uuid() {

        //given:
        GetScriptByUuidQuery getScriptByUuidQuery = new GetScriptByUuidQuery(uuid);

        //when:
        Optional<Script> scriptOpt = queryBus.execute(getScriptByUuidQuery);

        //then:
        assertNotEquals(Optional.empty(), scriptOpt);
        Script script = scriptOpt.get();
        assertEquals(xid, script.getXid());
        assertEquals(uuid, script.getUuid());

    }
}