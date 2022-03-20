package com.connectionHandlerService.service.connection;



// Generic interface
public interface Connection<T> {
    void doPlannedStateTasks(T connObj);
    void doLocalDesignStateTasks();
    void doImplementedStateTasks();
}
