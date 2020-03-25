package com.devwider.scada.domain.script.query;

import com.devwider.scada.cqrs.Query;
import com.devwider.scada.cqrs.QueryUnique;
import com.devwider.scada.domain.script.Script;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;
import java.util.UUID;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GetScriptByUuidQuery extends QueryUnique implements Query<Optional<Script>> {

    private final UUID scriptUuid;

    public GetScriptByUuidQuery(UUID scriptUuid) {
        this.scriptUuid = scriptUuid;
    }
}
