package com.devwider.scada.cqrs.bus;

import com.devwider.scada.cqrs.Query;

public interface QueryBus extends Bus<Query>, BusQueryExecutable {
}
