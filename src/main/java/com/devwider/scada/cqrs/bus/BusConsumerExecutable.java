package com.devwider.scada.cqrs.bus;

interface BusConsumerExecutable<T> {
    void execute(T object);
    void execute(T object, T... objects);
}
