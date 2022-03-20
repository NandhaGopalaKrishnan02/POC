package com.connectionHandlerService.service.connection;

import com.connectionHandlerService.MessageQ.MessageQueue;
import com.connectionHandlerService.model.ServiceConn.ServiceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "ServiceConnection")
public class ServiceConn implements Connection<ServiceInput>{


    @Autowired
    private Validate validate;

    @Autowired
    private MessageQueue messageQueue;
    private ServiceInput serviceInput;

    @Override
    public void doPlannedStateTasks(ServiceInput serviceInput) {
        this.serviceInput = serviceInput;
        String hasErrors = validate.doBasicConnValidation("SERVICE");
        System.out.println("error: "+hasErrors);
        if(hasErrors.length() > 0){
            System.out.println("send planned state as failed to client");
        } else {
            System.out.println("send planned is successful then change state to local design as in progress");
            doLocalDesignStateTasks();
        }
    }

    @Override
    public void doLocalDesignStateTasks() {
        System.out.println("LOCAL DESIGN....");
        System.out.println("send request to connection service to update NC, SO tables");
        System.out.println("send local design is successful ");
        doImplementedStateTasks();
    }

    @Override
    public void doImplementedStateTasks() {
        System.out.println("IMPLEMENTED....");
        System.out.println("call to check ne is up or not");
        System.out.println("update tp, ncComponent and so tables");
        System.out.println("Implemented state is successful. move the state to In effect");
    }

}
