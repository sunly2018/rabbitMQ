package com.pingan.zhxz.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String msgString  = "directSender : 交换机测试消息-sunli";
        System.out.println(msgString);
        amqpTemplate.convertAndSend("direct",msgString);
    }
}
