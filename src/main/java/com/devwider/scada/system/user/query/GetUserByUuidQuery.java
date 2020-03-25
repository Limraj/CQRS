package com.devwider.scada.system.user.query;

import com.devwider.scada.cqrs.Query;
import com.devwider.scada.cqrs.QueryUnique;
import com.devwider.scada.system.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;
import java.util.UUID;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GetUserByUuidQuery extends QueryUnique implements Query<Optional<User>> {

    private final UUID uuid;

    public GetUserByUuidQuery(UUID uuid) {
        this.uuid = uuid;
    }
}
