package com.connectionService.MessageQ;

import com.connectionService.model.DlConn.DLInput;
import com.connectionService.model.InfraConn.InfraInput;
import com.connectionService.model.ServiceConn.ServiceInput;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
public class Consumer {

    @RabbitListener(queues = "${messageQ.DL.Queue}")
    public void consumeMessageFromDLQueue(String msg) {
        System.out.println("Message recieved from queue dl : "+msg);
    }
    @RabbitListener(queues = "${messageQ.Infra.Queue}")
    public void consumeMessageFromInfraQueue(InfraInput infraInput) {
        System.out.println("Message recieved from queue : " + infraInput);
    }

    @RabbitListener(queues = "${messageQ.Service.Queue}")
    public void consumeMessageFromServiceQueue(ServiceInput serviceInput) {
        System.out.println("Message recieved from queue : " + serviceInput);
    }
}