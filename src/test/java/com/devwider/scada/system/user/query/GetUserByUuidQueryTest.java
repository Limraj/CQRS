package com.devwider.scada.system.user.query;

import com.devwider.scada.cqrs.bus.CommandBus;
import com.devwider.scada.cqrs.bus.CommandBusFactory;
import com.devwider.scada.cqrs.bus.QueryBus;
import com.devwider.scada.cqrs.bus.QueryBusFactory;
import com.devwider.scada.domain.TestUtil;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.command.AddUserCommand;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class GetUserByUuidQueryTest {

    private final QueryBus queryBus = QueryBusFactory.getInstance();
    private static final UUID uuid = UUID.randomUUID();

    @BeforeClass
    public static void before() {
        TestUtil.resetBuses(GetUserByUuidQueryTest.class);
        CommandBus commandBus = CommandBusFactory.getInstance();
        AddUserCommand addUserCommand = AddUserCommand.builder()
                .admin(true)
                .disabled(true)
                .email("abc@abc.com")
                .password("123")
                .phone("123456789")
                .username("Kamil")
                .userUuid(uuid)
                .build();
        AddUserCommand addUserCommand2 = AddUserCommand.builder()
                .admin(true)
                .disabled(true)
                .email("abc@abc.com")
                .password("123")
                .phone("123456789")
                .username("Paweł")
                .build();
        commandBus.execute(addUserCommand);
        commandBus.execute(addUserCommand2);
    }

    @AfterClass
    public static void after() {
        TestUtil.resetBuses(GetUserByUuidQueryTest.class);
    }

    @Test
    public void test_invoke_execute_for_get_user_by_uuid_query_then_user_with_uuid() {

        //given:
        GetUserByUuidQuery getUserByUuidQuery = new GetUserByUuidQuery(uuid);

        //when:
        Optional<User> users = queryBus.execute(getUserByUuidQuery);

        //then:
        assertNotEquals(Optional.empty(), users);
        User user = users.get();
        assertEquals(uuid, user.getUuid());
    }
}