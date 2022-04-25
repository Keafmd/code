package com.keafmd.springdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.keafmd.springdemo.pojo.User;
import com.keafmd.springdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IUserService userService;

    @GetMapping("/getUser/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    //更新用户的信息
    @PostMapping("/updateUser/{userId}")
    public Boolean updateUser(@PathVariable String userId) {
        //把num字段加5
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.<User>lambdaUpdate()
                .eq(User::getId, userId)
                .setSql("count = count + 5 + sleep(5) ");

        return userService.update(updateWrapper);
    }


}
