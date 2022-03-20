package com.connectionHandlerService.controller;


import com.connectionHandlerService.MessageQ.MessageQueue;
import com.connectionHandlerService.MessageQ.WebsocketHandler;
import com.connectionHandlerService.advice.ErrorDetails;
import com.connectionHandlerService.service.connection.Connection;
import com.connectionHandlerService.service.connection.Validate;
import com.connectionHandlerService.model.DlConn.DLInput;
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
/*
* when autowiring doesn't work
* ---------------------------
* https://www.moreofless.co.uk/spring-mvc-java-autowired-component-null-repository-service/
*
* Qualifier annotation
* --------------------
* 1.If there are multiple implementation for one interface then spring doesn't know which one to inject
*   if we autowire it. So qualifier annotation will help us th resolve the issue.
* link: https://www.baeldung.com/spring-qualifier-annotation
* */


@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/conn/dl")
public class DLController  {
	
	@Autowired
	RabbitTemplate template;

	@Autowired
	MessageQueue messageQueue;

	@Autowired
	Validate validate;

	@GetMapping("/InitiateConn")
	public ResponseEntity<?> initiateConnection(@RequestBody DLInput dlInputs) {
		System.out.println("************** DL ********************");
		String hasError = validate.doBasicValidation(dlInputs.getConnectionParameters());
		if(!hasError.equals("")){
			return ResponseEntity.ok().body(new ErrorDetails(new Date(), hasError, HttpStatus.BAD_REQUEST));
		}
		// Initiate conn creation
		messageQueue.sendMessage(dlInputs, messageQueue.DL_ROUTING_KEY);
		return ResponseEntity.ok().body(Response.ConnInitiated("Provisioning data sent to host"));
	}
}
