package com.connectionHandlerService.service.connection;

import com.connectionHandlerService.model.InfraConn.InfraInput;
import com.connectionHandlerService.request.ReqToConnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "InfraConnection")
public class InfraConn implements Connection<InfraInput>{

    @Autowired
    private Validate validate;

    @Autowired
    private ReqToConnService reqToConnService;

    private InfraInput infraInput;

    @Override
    public void doPlannedStateTasks(InfraInput infraInput) {
        this.infraInput = infraInput;

        reqToConnService.createServiceOrder(infraInput);
        String hasErrors = validate.doBasicConnValidation("INFRA");
        System.out.println("error: "+hasErrors);
        if( hasErrors.length() > 0){
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
