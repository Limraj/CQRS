package com.devwider.scada.domain.script.command;

import com.devwider.scada.cqrs.bus.CommandBus;
import com.devwider.scada.cqrs.bus.CommandBusFactory;
import com.devwider.scada.cqrs.bus.QueryBus;
import com.devwider.scada.cqrs.bus.QueryBusFactory;
import com.devwider.scada.domain.TestUtil;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.query.GetScriptByXidQuery;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class RemoveScriptCommandTest {

    private final QueryBus queryBus = QueryBusFactory.getInstance();
    private final CommandBus commandBus = CommandBusFactory.getInstance();
    private final static UUID uuid = UUID.randomUUID();
    private final static UUID otherUuid = UUID.randomUUID();
    private final static String xid = "xid_test";
    private final static String otherXid = "xid_test2";


    @BeforeClass
    public static void before() {
        TestUtil.resetBuses(RemoveScriptCommandTest.class);
        CommandBus commandBus = CommandBusFactory.getInstance();
        AddScriptCommand addOtherScript = AddScriptCommand.builder()
                .name("nameScript")
                .script("return 1 + 2")
                .userUuid(UUID.randomUUID())
                .scriptUuid(otherUuid)
                .xid(otherXid)
                .build();
        AddScriptCommand addScriptToRemove = AddScriptCommand.builder()
                .name("nameScript")
                .script("return 1 + 2")
                .userUuid(UUID.randomUUID())
                .scriptUuid(uuid)
                .xid(xid)
                .build();
        commandBus.execute(addOtherScript, addScriptToRemove);
    }

    @AfterClass
    public static void after() {
        TestUtil.resetBuses(RemoveScriptCommandTest.class);
    }

    @Test
    public void test_invoke_execute_for_remove_script_command_then_removed_this_script() {

        //given:
        GetScriptByXidQuery getScript = new GetScriptByXidQuery(xid);

        //when:
        Optional<Script> scriptBeforeRemovedOpt = queryBus.execute(getScript);

        //then:
        assertNotEquals(Optional.empty(), scriptBeforeRemovedOpt);

        Script scriptBeforeRemoved = scriptBeforeRemovedOpt.get();
        assertEquals(xid, scriptBeforeRemoved.getXid());
        assertEquals(uuid, scriptBeforeRemoved.getUuid());

        //and given:
        RemoveScriptCommand removeScriptCommand = new RemoveScriptCommand(scriptBeforeRemoved.getUuid());

        //when:
        commandBus.execute(removeScriptCommand);

        //and:
        Optional<Script> scriptAfterRemovedOpt = queryBus.execute(getScript);

        //then:
        assertEquals(Optional.empty(), scriptAfterRemovedOpt);

        //and given:
        GetScriptByXidQuery getOtherScriptNotRemoved = new GetScriptByXidQuery(otherXid);

        //when:
        Optional<Script> otherScriptNotRemovedOpt = queryBus.execute(getOtherScriptNotRemoved);

        //then:
        assertNotEquals(Optional.empty(), otherScriptNotRemovedOpt);
        Script otherScriptNotRemoved = otherScriptNotRemovedOpt.get();
        assertEquals(otherXid, otherScriptNotRemoved.getXid());
        assertEquals(otherUuid, otherScriptNotRemoved.getUuid());
    }
}