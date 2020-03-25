/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.command;

import com.devwider.scada.cqrs.bus.CommandBus;
import com.devwider.scada.cqrs.bus.CommandBusFactory;
import com.devwider.scada.cqrs.bus.QueryBus;
import com.devwider.scada.cqrs.bus.QueryBusFactory;
import com.devwider.scada.domain.TestUtil;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.query.GetUserByIdQuery;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.devwider.scada.system.user.query.GetUserByUsernameQuery;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;


/**
 *
 * @author Kamil-Tomasz
 */
public class AddUserCommandTest {
    
    private final QueryBus queryBus = QueryBusFactory.getInstance();
    private final CommandBus commandBus = CommandBusFactory.getInstance();

    @BeforeClass
    public static void before() {
        TestUtil.resetBuses(AddUserCommandTest.class);
    }
    
    @AfterClass
    public static void after() {
        TestUtil.resetBuses(AddUserCommandTest.class);
    }
    
    @Test
    public void test_invoke_execute_for_add_user_command_then_created_user() {
        
        //given:
        AddUserCommand addUserCommand = AddUserCommand.builder()
                .admin(true)
                .disabled(true)
                .email("abc@abc.com")
                .password("123")
                .phone("123456789")
                .username("Kamil")
                .userUuid(UUID.randomUUID())
                .build();
        
        //when:
        commandBus.execute(addUserCommand);

        //when:
        List<User> kamil = queryBus.execute(new GetUserByUsernameQuery("Kamil"));

        //then:
        assertNotEquals(Collections.emptyList(), kamil);
        
        //and:
        Optional<User> result = queryBus.execute(new GetUserByIdQuery(kamil.get(0).getId()));
        
        //then:
        assertNotEquals(Optional.empty(), kamil);
        User user = result.get();
        assertEquals(addUserCommand.getEmail(), user.getEmail());
        assertEquals(addUserCommand.getPassword(), user.getPassword());
        assertEquals(addUserCommand.getUsername(), user.getUsername());
        assertEquals(addUserCommand.getPhone(), user.getPhone());
        assertEquals(addUserCommand.isAdmin(), user.isAdmin());
        assertEquals(addUserCommand.isDisabled(), user.isDisabled());
        assertEquals(addUserCommand.getUserUuid(), user.getUuid());
    }
    
}
