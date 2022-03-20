package com.connectionHandlerService.MessageQ;


import com.connectionHandlerService.model.DlConn.DLInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
public class WebsocketHandler {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    public void sendMessageToClient (DLInput dl){
        System.out.println("sending...");
        messagingTemplate.convertAndSendToUser("nandhu","private", dl);

    }

}
