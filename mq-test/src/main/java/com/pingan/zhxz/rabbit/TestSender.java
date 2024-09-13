package com.pingan.zhxz.rabbit;

import com.pingan.zhxz.user.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message){
//        String sendMsg = "test" + new Date();
        System.out.println("Sender : " + message);
        for(int i=0;i<10;i++){
            rabbitTemplate.convertAndSend("testQueue",message+i);
        }
//        this.rabbitTemplate.convertAndSend("testQueue",message);
    }

    public void send(User user){
        System.out.println(""+user.getUserName()+"/"+user.getSex());
        rabbitTemplate.convertAndSend("userQueue",user);
    }
}
