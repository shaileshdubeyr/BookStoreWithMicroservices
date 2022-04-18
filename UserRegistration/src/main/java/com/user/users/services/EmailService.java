package com.user.users.services;

import com.google.gson.Gson;
import com.user.users.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String body){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shaileshdubeyr@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText("this is system generated mail "+ body);
        System.out.println("mail sent successfully");
        javaMailSender.send(simpleMailMessage);
    }

    public void updateMail(String to, String subject, String body){
        SimpleMailMessage simpleMailMessage = null;
        simpleMailMessage.setFrom("shaileshdubeyr@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText("this is system generated mail" +body);
        System.out.println("updated data successfully");
        javaMailSender.send(simpleMailMessage);
    }

    public String getLink(String token){
        return "http://localhost:8081/user/verify/" + token;
    }

    public String forgetPassword(UserData userData, String token){

        return "curl -i \\\n" +
                "-H \"Accept: application/json\" \\\n" +
                "-H \"Content-Type:application/json\" \\\n" +
                "-X POST --data \n" +
                new Gson().toJson(userData) +"http://localhost:8080/user/reset/password?" + token;
    }

    public String cancelOrder(int id){
        return "http://localhost:8080/order/cancel/" + id;
    }
}
