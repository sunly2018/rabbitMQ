package com.pingan.zhxz.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send(){
        String msg1 = "I am topic.message msg======";
        System.out.println("sender1 : " + msg1);
        amqpTemplate.convertAndSend("exchange","topic.message",msg1);

        String msg2 = "I am topic.messages msg######";
        System.out.println("sender2 : "+msg2);
        amqpTemplate.convertAndSend("exchange","topic.messages",msg2);
    }

}
