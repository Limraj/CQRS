/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user.query;

import com.devwider.scada.cqrs.Query;
import com.devwider.scada.cqrs.QueryUnique;
import com.devwider.scada.system.user.User;
import java.util.List;

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
public class GetUserByUsernameQuery extends QueryUnique implements Query<List<User>> {
    
    private final String username;

    public GetUserByUsernameQuery(String name) {
        this.username = name;
    }
    
}
