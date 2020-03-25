/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.event.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.event.AddedUserEvent;
import com.devwider.scada.common.repository.RepositoryRelational;
import com.devwider.scada.cqrs.handler.EventHandlable;

/**
 *
 * @author Kamil-Tomasz
 */
class AddedUserEventHandler extends AbstractUserEventHandler implements EventHandlable<AddedUserEvent> {

    public AddedUserEventHandler(Repository<User> repository) {
        super(repository);
    }

    @Override
    public void handle(AddedUserEvent addedUserEvent) {
        getRepository().create(addedUserEvent.getUser());
    }
}
