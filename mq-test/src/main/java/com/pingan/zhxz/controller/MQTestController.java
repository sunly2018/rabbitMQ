package com.pingan.zhxz.controller;

import com.pingan.zhxz.rabbit.DirectSender;
import com.pingan.zhxz.rabbit.FanoutSender;
import com.pingan.zhxz.rabbit.TestSender;
import com.pingan.zhxz.rabbit.TopicSender;
import com.pingan.zhxz.user.dao.UserDao;
import com.pingan.zhxz.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MQTestController {

    @Autowired
    private TestSender sender;
    @Autowired
    private DirectSender directSender;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;

    @RequestMapping("/mqtest/{message}")
    @ResponseBody
    public String test(@PathVariable("message")String message){
        sender.send(message);
        return "mqtest success!";
    }

    @RequestMapping("/userTest")
    @ResponseBody
    public String test(){
//        User user1 = new User();
//        user1.setUserName("孙立");
//        user1.setSex("男");
//        User user2 = new User();
//        user2.setUserName("刘姣");
//        user2.setSex("女");
//        sender.send(user1);
//        sender.send(user2);
        for(int i=0;i<1000000;i++){
            User user = new User();
            user.setUserName("孙立"+i);
            user.setSex("男");
            sender.send(user);
        }
        return "mqtest success!";
    }

    @RequestMapping("/directTest")
    @ResponseBody
    public String direct(){
        directSender.send();
        return "mqtest success!";
    }

    @RequestMapping("/topicTest")
    @ResponseBody
    public String topic(){
        topicSender.send();
        return "topic success!";
    }

    @RequestMapping("/fanoutTest")
    @ResponseBody
    public String fanout(){
        fanoutSender.send();
        return "fanout success!";
    }


}
