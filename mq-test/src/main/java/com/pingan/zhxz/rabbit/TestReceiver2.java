package com.pingan.zhxz.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="testQueue")
//RabbitListener注解是监听队列的，当队列有消息的时候，它会自动获取。
//标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，
// 具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
public class TestReceiver2 {

    @RabbitHandler
    public void process(String test){
        System.out.println("Receiver2: "+test);
    }
}
