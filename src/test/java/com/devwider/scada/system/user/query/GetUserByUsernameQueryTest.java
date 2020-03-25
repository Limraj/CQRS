/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.query;

import com.devwider.scada.cqrs.bus.CommandBus;
import com.devwider.scada.cqrs.bus.CommandBusFactory;
import com.devwider.scada.cqrs.bus.QueryBus;
import com.devwider.scada.cqrs.bus.QueryBusFactory;
import com.devwider.scada.domain.TestUtil;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.command.AddUserCommand;
import java.util.Collections;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kamil-Tomasz
 */
public class GetUserByUsernameQueryTest {
    
    private final QueryBus queryBus = QueryBusFactory.getInstance();

    @BeforeClass
    public static void before() {
        TestUtil.resetBuses(GetUserByUsernameQueryTest.class);
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
                .username("Paweł")
                .build();
        commandBus.execute(addUserCommand);
        commandBus.execute(addUserCommand2);
    }
    
    @AfterClass
    public static void after() {
        TestUtil.resetBuses(GetUserByUsernameQueryTest.class);
    }
    
    @Test
    public void test_invoke_execute_for_get_user_by_username_query_then_user_with_username() {
        
        //given:
        String username = "Kamil";
        GetUserByUsernameQuery getUserByUsernameQuery = new GetUserByUsernameQuery(username);
        
        //when:
        List<User> users = queryBus.execute(getUserByUsernameQuery);
        System.out.println("users: " + users);
        
        //then:
        assertNotEquals(Collections.emptyList(), users);
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("Kamil", user.getUsername());
    }
    
}
