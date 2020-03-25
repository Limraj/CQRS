package com.devwider.scada.cqrs.bus;

import com.devwider.scada.cqrs.Query;

interface BusQueryExecutable {
    <R> R execute(Query<R> query);
}
