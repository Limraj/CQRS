package com.devwider.scada.domain.script.query.handler;

import com.devwider.scada.common.repository.Repository;
import com.devwider.scada.cqrs.handler.QueryHandlable;
import com.devwider.scada.domain.script.Script;
import com.devwider.scada.domain.script.query.GetScriptByUuidQuery;

import java.util.Optional;
import java.util.UUID;

public class GetScriptByUuidQueryHandler extends AbstractScriptQueryHandler
        implements QueryHandlable<Optional<Script>, GetScriptByUuidQuery> {

    public GetScriptByUuidQueryHandler(Repository<Script> repository) {
        super(repository);
    }

    @Override
    public Optional<Script> handle(GetScriptByUuidQuery query) {
        UUID uuid = query.getScriptUuid();
        return Optional.ofNullable(getRepository().read(uuid == null ? "" : uuid.toString()));
    }
}
