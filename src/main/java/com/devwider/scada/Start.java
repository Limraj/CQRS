/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.Command;
import com.devwider.scada.cqrs.bus.CommandBus;
import com.devwider.scada.cqrs.bus.CommandBusFactory;
import com.devwider.scada.cqrs.bus.EventBusFactory;
import com.devwider.scada.cqrs.bus.QueryBus;
import com.devwider.scada.cqrs.bus.QueryBusFactory;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.command.AddUserCommand;
import com.devwider.scada.system.user.command.RemoveUserCommand;
import com.devwider.scada.system.user.command.repository.UserCommandRepository;
import com.devwider.scada.system.user.query.GetUserByIdQuery;
import com.devwider.scada.system.user.query.GetUserByUsernameQuery;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.command.AddScriptCommand;
import com.devwider.scada.domain.script.query.GetScriptByXidQuery;
import com.devwider.scada.system.user.query.repository.UserQueryRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author Kamil-Tomasz
 */
public class Start {
    
    public static void main(String[] args) {
        Repository<User> queryRepository = UserQueryRepository.INSTANCE;
        Repository<User> commandRepository = UserCommandRepository.INSTANCE;
        CommandBus commandBus = CommandBusFactory.getInstance();
        commandBus.reset();
        QueryBus queryBus = QueryBusFactory.getInstance();
        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        Command addUserCommand = AddUserCommand.builder()
                .admin(true)
                .disabled(true)
                .email("abc@abc.com")
                .password("123")
                .phone("123456789")
                .username("Kamil")
                .userUuid(uuid)
                .build();
        Command addUserCommand2 = AddUserCommand.builder()
                .admin(true)
                .disabled(true)
                .email("abc@abc.com")
                .password("123")
                .phone("123456789")
                .username("Arek")
                .userUuid(uuid2)
                .build();
        Command addUserCommand3 = AddUserCommand.builder()
                .admin(true)
                .disabled(true)
                .email("abc@abc.com")
                .password("123")
                .phone("123456789")
                .username("Ewka")
                .build();
        
        commandBus.execute(addUserCommand);
        commandBus.execute(addUserCommand);
        
        System.out.println("query repo: " + queryRepository.readAll());
        System.out.println("command repo: " + commandRepository.readAll());
        
        commandBus.execute(addUserCommand2);
        commandBus.execute(addUserCommand);
        
        System.out.println("query repo: " + queryRepository.readAll());
        System.out.println("command repo: " + commandRepository.readAll());
        
        commandBus.execute(addUserCommand3);
        commandBus.execute(addUserCommand);
        commandBus.execute(addUserCommand);

        System.out.println("query repo: " + queryRepository.readAll());
        System.out.println("command repo: " + commandRepository.readAll());

        GetUserByIdQuery getUserQuery = new GetUserByIdQuery(0);
        commandBus.execute(new RemoveUserCommand(UUID.fromString("12345-3445-44-465-565")));
      
        Optional<User> user = queryBus.execute(getUserQuery);
        
        GetUserByUsernameQuery getUserByNameQuery = new GetUserByUsernameQuery("kamil");
        List<User> users = queryBus.execute(getUserByNameQuery);
        
        System.out.println("User: " + user);
        System.out.println("Users: " + users);
        System.out.println("query repo: " + queryRepository.readAll());
        System.out.println("command repo: " + commandRepository.readAll());
        
        Command command = AddScriptCommand.builder()
                .name("moj")
                .script("a == b")
                .userUuid(UUID.randomUUID())
                .xid("DF_FFDGS56")
                .build();
        
        

        EventBusFactory.getInstance().readExecuted().forEach(System.out::println);
        CommandBusFactory.getInstance().readExecuted().forEach(System.out::println);
        QueryBusFactory.getInstance().readExecuted().forEach(System.out::println);
        
        GetScriptByXidQuery getScriptByXidQuery = new GetScriptByXidQuery("adgad");
        Optional cos = queryBus.execute(getUserQuery);
        Optional<Script> script = queryBus.execute(getScriptByXidQuery);
        System.out.println("_______________________________________");
        QueryBusFactory.getInstance().readExecuted().stream().forEach(System.out::println);
        EventBusFactory.getInstance().readExecuted().stream().forEach(System.out::println);
        System.out.println("_______________________________________");


    }



}
