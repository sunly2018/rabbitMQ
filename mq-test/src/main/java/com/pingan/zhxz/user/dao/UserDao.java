package com.pingan.zhxz.user.dao;

import com.pingan.zhxz.user.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    Integer createUser(User user);

    Integer getIdByName(@Param("userName") String userName);
}
