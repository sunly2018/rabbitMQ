package com.pingan.zhxz.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String msgString = "fanoutSender : hello,i am fountSender message";
        System.out.println(msgString);
        //参数2被忽略
        amqpTemplate.convertAndSend("fanoutExchange","",msgString);
    }
}
