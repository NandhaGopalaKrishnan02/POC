package com.connectionService.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.*;

@Configuration
@Component
public class MessageQConfig {

    @Value("${messageQ.Exchange}")
    public  String EXCHANGE;

	@Value("${messageQ.DL.Queue}")
	public  String DL_QUEUE;
	
	@Value("${messageQ.DL.RoutingKey}")
    public  String DL_ROUTING_KEY;

    @Value("${messageQ.Infra.Queue}")
    public  String INFRA_QUEUE;

    @Value("${messageQ.Infra.RoutingKey}")
    public  String INFRA_ROUTING_KEY;

    @Value("${messageQ.Service.Queue}")
    public  String SERVICE_QUEUE;

    @Value("${messageQ.Service.RoutingKey}")
    public  String SERVICE_ROUTING_KEY;


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue DL_queue() {
        return new Queue(DL_QUEUE);
    }
    @Bean
    public Binding DL_binding(Queue DL_queue, TopicExchange exchange) {
        return BindingBuilder.bind(DL_queue).to(exchange).with(DL_ROUTING_KEY);
    }


    @Bean
    public Queue Infra_queue() {
        return new Queue(INFRA_QUEUE);
    }
    @Bean
    public Binding Infra_binding(Queue Infra_queue, TopicExchange exchange) {
        return BindingBuilder.bind(Infra_queue).to(exchange).with(INFRA_ROUTING_KEY);
    }

    @Bean
    public Queue Service_queue() {
        return new Queue(SERVICE_QUEUE);
    }

    @Bean
    public Binding Service_binding(Queue Service_queue, TopicExchange exchange) {
        return BindingBuilder.bind(Service_queue).to(exchange).with(SERVICE_ROUTING_KEY);
    }
    
	@Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();

    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
