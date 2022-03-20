package com.connectionHandlerService.service.connection;

import com.connectionHandlerService.MessageQ.MessageQueue;
import com.connectionHandlerService.MessageQ.WebsocketHandler;
import com.connectionHandlerService.model.DlConn.DLInput;
import com.connectionHandlerService.request.ReqToConnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "DLConnection")
public class DLConn implements Connection<DLInput>{

    @Autowired
    private Validate validate;

    @Autowired
    private MessageQueue messageQueue;

    @Autowired
    private WebsocketHandler websocketHandler;

    @Autowired
    private ReqToConnService reqToConnService;

    private DLInput dlInput;
    private int currentOrderId;

    @Override
    public void doPlannedStateTasks(DLInput dlInput) {
        this.dlInput = dlInput;

        // create order
         reqToConnService.createServiceOrder(dlInput);

        String hasErrors = validate.doBasicConnValidation("DL");
        System.out.println("error: "+hasErrors);
        if( hasErrors.length() > 0 ){
            System.out.println("send planned state as failed to client");
            // send error message directly to client
        } else {
            System.out.println("send planned is successful then change state to local design as in progress");
            doLocalDesignStateTasks();
        }
    }

    @Override
    public void doLocalDesignStateTasks() {

        System.out.println("LOCAL DESIGN....");
        System.out.println("send request to connection service to update NC, tp, ncComponent and so tables SO tables");
        System.out.println("send local design is successful ");
        // send message to connection service to update tables
        //
        doImplementedStateTasks();
    }

    @Override
    public void doImplementedStateTasks() {
        System.out.println("IMPLEMENTED....");
        System.out.println("call to check ne is up or not");
        System.out.println("Update connection details in ne db");
        System.out.println("Implemented state is successful. move the state to In effect");
    }


}
