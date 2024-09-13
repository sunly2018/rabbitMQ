package com.pingan.zhxz.rabbit;

import com.pingan.zhxz.user.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "userQueue")
public class UserReceiver {

    @RabbitHandler
    public void process(User user){
        System.out.println("user receive : " + user.getUserName() + "/" +user.getSex());
    }
}
