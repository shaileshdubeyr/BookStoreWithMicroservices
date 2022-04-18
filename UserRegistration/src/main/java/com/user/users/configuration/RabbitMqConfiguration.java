package com.user.users.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class RabbitMqConfiguration {

    public  static final String ROUTING_A = "Routing_A";
    public  static final String EXCHANGE = "exchange_direct";
    public  static final String QUEUE = "BookQueue";

    @Bean
    Queue getQueueA(){
        return new Queue(QUEUE, false);
    }


    @Bean
    public TopicExchange getDirectExchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding getBongingA(Queue queueA, TopicExchange exchange){
        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_A);
    }

    @Bean
    public MessageConverter getMessageConvertor(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate getRabbitTemplet(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getMessageConvertor());
        return rabbitTemplate;
    }
}
