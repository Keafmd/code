package com.keafmd.springdemo.controller;

import com.keafmd.springdemo.pojo.User;
import com.keafmd.springdemo.service.IUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Keafmd
 *
 * @ClassName: UserController
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-07 11:54
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/getUser/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getById(userId);
    }

}
