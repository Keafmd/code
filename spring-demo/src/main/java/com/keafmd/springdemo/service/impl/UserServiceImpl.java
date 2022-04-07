package com.keafmd.springdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.keafmd.springdemo.mapper.UserMapper;
import com.keafmd.springdemo.pojo.User;
import com.keafmd.springdemo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Keafmd
 *
 * @ClassName: UserServiceImpl
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-07 11:47
 */
@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
