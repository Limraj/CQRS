/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.event.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.system.user.User;
import com.devwider.scada.system.user.event.RemovedUserEvent;
import com.devwider.scada.cqrs.handler.EventHandlable;

import java.util.UUID;

/**
 *
 * @author Kamil-Tomasz
 */
class RemovedUserEventHandler extends AbstractUserEventHandler implements EventHandlable<RemovedUserEvent> {

    public RemovedUserEventHandler(Repository<User> repository) {
        super(repository);
    }

    @Override
    public void handle(RemovedUserEvent removedUserEvent) {
        User user = removedUserEvent.getUser();
        UUID uuid = user.getUuid();
        getRepository().remove(uuid == null ? "" : uuid.toString());
    }

}
