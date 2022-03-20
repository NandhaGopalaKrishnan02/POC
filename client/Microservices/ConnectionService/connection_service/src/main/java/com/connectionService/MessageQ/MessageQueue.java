package com.connectionService.MessageQ;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class MessageQueue {


    @Value("${DL.messageQ.Exchange}")
    public   String EXCHANGE="ConnExchange";

    @Value("${DL.messageQ.RoutingKey}")
    public   String ROUTING_KEY="DLConnection";

    public  void sendMessage(RabbitTemplate template,String msg){

        template.setMandatory(true);
        template.setConfirmCallback((correlation, ack, reason) -> {
            if (correlation != null) {
                System.out.println("Received " + (ack ? " ack " : " nack ") + "for correlation: " + correlation);
            }
        });

        template.convertAndSend(EXCHANGE, ROUTING_KEY, msg,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));
    }
}
