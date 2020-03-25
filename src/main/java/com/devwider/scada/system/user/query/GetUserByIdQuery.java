/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.query;

import com.devwider.scada.cqrs.Query;
import com.devwider.scada.cqrs.QueryUnique;
import com.devwider.scada.system.user.User;
import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Kamil-Tomasz
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GetUserByIdQuery extends QueryUnique implements Query<Optional<User>> {

    private final long userId;

    public GetUserByIdQuery(long userId) {
        this.userId = userId;
    }
}
