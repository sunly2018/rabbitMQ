package com.pingan.zhxz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//
@Configuration
public class MQConfig {
    private static Logger LOG = LoggerFactory.getLogger(MQConfig.class);

    @Autowired
    private CachingConnectionFactory connectionFactory;

    final static String queueName = "testQueue";

//    Bean默认的name是方法名
    @Bean
    public Queue testQueue(){
        System.out.println("testQueue----------------->sunli");
        return new Queue(queueName);
    }

    @Bean
    public Queue userQueue(){
        return new Queue("userQueue");
    }

    @Bean
    public Queue dirQueue(){
        return new Queue("direct");
    }

    @Bean(name = "message")
    public Queue queueMessage(){
        return new Queue("topic.message");
    }

    @Bean(name = "messages")
    public Queue queueMessages(){
        return new Queue("topic.messages");
    }

    @Bean(name="AMessage")
    public Queue AMessage(){
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage(){
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage(){
        return new Queue("fanout.C");
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }


    @Bean
    Binding bindingExchangeDirect(@Qualifier("dirQueue")Queue dirQueue,DirectExchange directExchange){
        System.out.println("sunli:dirQueue--->"+dirQueue);
        System.out.println("sunli:directExchange--->"+directExchange);
        return BindingBuilder.bind(dirQueue).to(directExchange).with("direct");
    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("message")Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(@Qualifier("messages")Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

    @Bean
    Binding bindingExchangeA(@Qualifier("AMessage")Queue AMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate  = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack){
                    LOG.info("消息发送成功：correlationData({}),ack({}),cause({})",correlationData,ack,cause);
                }else {
                    LOG.info("消息发送失败：correlationData({}),ack({}),cause({})",correlationData,ack,cause);
                }
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback(){
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                LOG.info("消息丢失：exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }

}



