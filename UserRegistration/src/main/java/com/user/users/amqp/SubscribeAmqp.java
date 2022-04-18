package com.user.users.amqp;

import com.user.users.dto.UserDTO;
import com.user.users.services.EmailService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscribeAmqp {

    @Autowired
    EmailService serviceEmail;

    @RabbitListener(queues = AmqpConfiguration.QUEUE)
    public void getUserDTO(String token, UserDTO userDTO){
        serviceEmail.sendMail("registration mail",userDTO.eMail,serviceEmail.getLink(token));
    }
}
