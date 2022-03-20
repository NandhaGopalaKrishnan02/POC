package com.connectionHandlerService.controller;


import com.connectionHandlerService.MessageQ.MessageQueue;
import com.connectionHandlerService.advice.ErrorDetails;
import com.connectionHandlerService.service.connection.Connection;
import com.connectionHandlerService.service.connection.Validate;
import com.connectionHandlerService.model.InfraConn.InfraInput;
import com.connectionHandlerService.response.Response;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/conn/infra")
public class InfraController {

    @Autowired
    RabbitTemplate template;

    @Autowired
    MessageQueue messageQueue;

    @Autowired
    Validate validate;


    @GetMapping("/InitiateConn")
    public ResponseEntity<?> initiateConnection(@RequestBody InfraInput infraInput) {
        String hasError = validate.doBasicValidation(infraInput.getConnectionParameters());
        System.out.println("************** INFRA ********************");
        if(!hasError.equals("")){
            return ResponseEntity.ok().body(new ErrorDetails(new Date(), hasError, HttpStatus.BAD_REQUEST));
        }

        // Initiate conn creation
        messageQueue.sendMessage(infraInput, messageQueue.INFRA_ROUTING_KEY);
        return ResponseEntity.ok().body(Response.ConnInitiated("Provisioning data sent to host"));

    }
}
