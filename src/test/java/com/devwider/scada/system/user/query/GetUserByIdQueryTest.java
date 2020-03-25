/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.query;

import com.devwider.scada.cqrs.bus.CommandBus;
import com.devwider.scada.cqrs.bus.CommandBusFactory;
import com.devwider.scada.cqrs.bus.EventBus;
import com.devwider.scada.cqrs.bus.EventBusFactory;
import com.devwider.scada.cqrs.bus.QueryBus;
import com.devwider.scada.cqrs.bus.QueryBusFactory;
import com.devwider.scada.domain.TestUtil;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.command.AddUserCommand;
import com.devwider.scada.system.user.event.AddedUserEvent;
import java.util.List;
import java.util.Optional;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kamil-Tomasz
 */
public class GetUserByIdQueryTest {
    
    private final QueryBus queryBus = QueryBusFactory.getInstance();
    private static long userId;
    
    @BeforeClass
    public static void before() {
        TestUtil.resetBuses(GetUserByIdQueryTest.class);
        CommandBus commandBus = CommandBusFactory.getInstance();
        EventBus eventBus = EventBusFactory.getInstance();
        AddUserCommand addUserCommand = AddUserCommand.builder()
                .admin(true)
                .disabled(true)
                .email("abc@abc.com")
                .password("123")
                .phone("123456789")
                .username("Kamil")
                .build();
        commandBus.execute(addUserCommand);
        commandBus.execute(addUserCommand);
        commandBus.execute(addUserCommand);
        List<AddedUserEvent> event = eventBus.getEventsForType(AddedUserEvent.class);
        eventBus.getEventsForCommand(addUserCommand);
        userId = event.get(0).getUser().getId();
        
    }
    
    @AfterClass
    public static void after() {
        TestUtil.resetBuses(GetUserByIdQueryTest.class);
    }

    @Test
    public void test_invoke_execute_for_get_user_by_id_query_then_user_with_id() {
        
        //given:
        GetUserByIdQuery getUserByIdQuery = new GetUserByIdQuery(userId);
        
        //when:
        Optional<User> scriptOpt = queryBus.execute(getUserByIdQuery);

        //then:
        assertNotEquals(Optional.empty(), scriptOpt);
        User user = scriptOpt.get();
        assertEquals(userId, user.getId());
    }
    
}
