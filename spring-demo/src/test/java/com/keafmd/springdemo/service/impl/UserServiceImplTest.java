package com.keafmd.springdemo.service.impl;

import com.keafmd.springdemo.pojo.User;
import com.keafmd.springdemo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    IUserService userService;

    @Test
    void insertUser(){
        User user = new User();
        user.setName("keafmd1")
                .setCount(100);
        userService.save(user);

        User user2 = new User();
        user2.setName("keafmd2")
                .setCount(100);
        userService.save(user2);

    }

    @Test
    void transfer() throws SQLException {
        userService.transfer("1513338722606751746", "1513338724586463233", 10);
    }


}