package com.user.users.amqpreceive;

import com.user.users.configuration.RabbitMqConfiguration;
import com.user.users.model.UserData;
import com.user.users.services.EmailService;
import com.user.users.utility.UserUtility;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author shailesh dubey
 * @Version 16.0.2
 * @Since 24-03-2022
 * @purpose to get data data from Rabbitmq server
 */
@Component
public class SubscribeMessage {
   /**
     *
     * @param userDTO getting from server
     * @return no return type
    */
   @Autowired
   EmailService service;

   @Autowired
   UserUtility userUtility;

    /**
     *
     * @param userData to send a email
     * @return no return type
     * @Purpose to subscribe the data and send the mail
     */
   @RabbitListener(queues = RabbitMqConfiguration.QUEUE)
   public void getUserDto(UserData userData){
       //creating token
       String token = userUtility.createToken(userData.getId());
       //sending mail
       service.sendMail(userData.getEMail(),"mail verification", service.getLink(token));
   }
}
