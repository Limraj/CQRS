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
import com.devwider.scada.system.user.query.GetUserByUsernameQuery;
import java.util.Collections;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Kamil-Tomasz
 */
public class RemoveUserCommandTest {

    private final QueryBus queryBus = QueryBusFactory.getInstance();
    private final CommandBus commandBus = CommandBusFactory.getInstance();

    @BeforeClass
    public static void before() {
        TestUtil.resetBuses(RemoveUserCommandTest.class);
        CommandBus commandBus = CommandBusFactory.getInstance();
        AddUserCommand addUserCommand = AddUserCommand.builder()
                .admin(true)
                .disabled(true)
                .email("abc@abc.com")
                .password("123")
                .phone("123456789")
                .username("Kamil")
                .build();
        AddUserCommand addUserCommand2 = AddUserCommand.builder()
                .admin(true)
                .disabled(true)
                .email("abc@abc.com")
                .password("123")
                .phone("123456789")
                .username("Arek")
                .build();
        commandBus.execute(addUserCommand, addUserCommand2);
    }
    
    @AfterClass
    public static void after() {
        TestUtil.resetBuses(RemoveUserCommandTest.class);
    }

    @Test
    public void test_invoke_execute_for_remove_user_command_then_removed_user() {
        
        //given:
        GetUserByUsernameQuery getUserByUsernameQuery = new GetUserByUsernameQuery("Kamil");
        
        //when:
        List<User> userBeforeRemove = queryBus.execute(getUserByUsernameQuery);

        //then:
        assertEquals(1, userBeforeRemove.size());
        
        User kamil = userBeforeRemove.get(0);
        assertEquals("Kamil", kamil.getUsername());
        
        //and given:
        RemoveUserCommand removeUserCommand = new RemoveUserCommand(kamil.getUuid());
        
        //when:
        commandBus.execute(removeUserCommand);
        
        //and:
        List<User> userAfterRemove = queryBus.execute(getUserByUsernameQuery);
        
        //then:
        assertEquals(Collections.emptyList(), userAfterRemove);
        
        //and given:
        GetUserByUsernameQuery getUserByUsernameQueryArek = new GetUserByUsernameQuery("Arek");
        
        //when:
        List<User> userArek = queryBus.execute(getUserByUsernameQueryArek);

        //then:
        assertEquals(1, userArek.size());
        
        User arek = userArek.get(0);
        assertEquals("Arek", arek.getUsername());
       
    }
    
}
