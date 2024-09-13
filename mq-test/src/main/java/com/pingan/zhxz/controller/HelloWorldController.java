package com.pingan.zhxz.controller;

import com.pingan.zhxz.user.dao.UserDao;
import com.pingan.zhxz.user.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloWorldController {

    @Resource
    private UserDao userDao;


    @RequestMapping("/index/{name}")
    @ResponseBody
    public String index(@PathVariable String name){

        if(null==name){
            name="boy";
            return "hello world " +name + "!";
        }
        Integer userId = userDao.getIdByName(name);
        if(userId==null){
            User user = new User();
            user.setUserName(name);
            user.setPhone("123124141241");
            userDao.createUser(user);
            userId = user.getUserId();
        }
        return "hello world " +userId + "!";
    }


    @RequestMapping("/index/user")
    @ResponseBody
    public User getUser(){
        User user = new User();
        user.setUserName("sunli");
        user.setPhone("123124141241");
        user.setUserId(1000);
        return user;
    }


}