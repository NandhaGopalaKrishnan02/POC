package com.connectionHandlerService.MessageQ;

import com.connectionHandlerService.model.DlConn.DLInput;
import com.connectionHandlerService.model.InfraConn.InfraInput;
import com.connectionHandlerService.model.ServiceConn.ServiceInput;
import com.connectionHandlerService.service.connection.Connection;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Consumer {


    @Autowired
    @Qualifier("DLConnection")
    Connection<DLInput> dlConn;

    @Autowired
    @Qualifier("InfraConnection")
    Connection<InfraInput> infraConn;

    @Autowired
    @Qualifier("ServiceConnection")
    Connection<ServiceInput> serviceConn;

    @RabbitListener(queues = "${messageQ.ConnHandler.DL.Queue}")
    public void consumeMessageFromDLQueue(DLInput dlInputs){
        dlConn.doPlannedStateTasks(dlInputs);
    }

    @RabbitListener(queues = "${messageQ.ConnHandler.Infra.Queue}")
    public void consumeMessageFromInfraQueue(InfraInput infraInputs){
        infraConn.doPlannedStateTasks(infraInputs);
    }

    @RabbitListener(queues = "${messageQ.ConnHandler.Service.Queue}")
    public void consumeMessageFromServiceQueue(ServiceInput serviceInputs){
        serviceConn.doPlannedStateTasks(serviceInputs);
    }
}