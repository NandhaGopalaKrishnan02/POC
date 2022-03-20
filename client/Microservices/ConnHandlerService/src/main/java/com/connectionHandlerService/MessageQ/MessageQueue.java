package com.connectionHandlerService.MessageQ;

import lombok.Data;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class MessageQueue {


    @Value("${messageQ.ConnHandler.Exchange}")
    public   String EXCHANGE;

    @Value("${messageQ.ConnHandler.DL.RoutingKey}")
    public  String DL_ROUTING_KEY;

    @Value("${messageQ.ConnHandler.Infra.RoutingKey}")
    public  String INFRA_ROUTING_KEY;

    @Value("${messageQ.ConnHandler.Service.RoutingKey}")
    public  String SERVICE_ROUTING_KEY;

    @Autowired
    private RabbitTemplate template;


    public  void sendMessage(Object msg, String routingKey){
        /**
         * Ensure that the message can be returned to the queue if it fails to send
         * Note: yml needs to configure publisher-confirm-type: correlated
         */
        template.setMandatory(true);

        /**
         * After the consumer confirms that the message has been received, the manual ack receipt callback is processed
         */
//        template.setConfirmCallback(new ConfirmCallbackService());


        template.setConfirmCallback((correlation, ack, reason) -> {
            if (correlation != null) {
                System.out.println("Received " + (ack ? " ack " : " nack ") + "for correlation: " + correlation);
            }
        });

        template.convertAndSend(EXCHANGE, routingKey, msg,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    /*
                    * Delivery mode
                    * ------------
                    *  It decides where to store our messages, whether queue should store our messages in memory(RAM) or disk(permanent storage)
                    *
                    * */

                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));
    }


}
