package com.InfraService.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/infra")
public class InfraController {
	
	@Value("${Infra.messageQ.Exchange}")
    public  String EXCHANGE;
	
	@Value("${Infra.messageQ.RoutingKey}")
    public  String ROUTING_KEY;
	
	@Autowired
	private RabbitTemplate template;
	
	@GetMapping("/testAuth")
	public ResponseEntity<?> testAuthorization() {
		template.convertAndSend(EXCHANGE, ROUTING_KEY, "Message sent from infra");
		return ResponseEntity.ok().body("Access granted");
		
	}
}
