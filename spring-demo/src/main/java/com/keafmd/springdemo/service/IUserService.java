package com.keafmd.springdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.keafmd.springdemo.pojo.User;

import java.sql.SQLException;

/**
 * Keafmd
 *
 * @ClassName: IUserService
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-07 11:47
 */
public interface IUserService extends IService<User> {
    //转账
    void transferError(String outUser, String inUser, Integer money) throws SQLException;

    void transfer(String outUser, String inUser, Integer money) throws SQLException;

    void transferEasy(String outUser, String inUser, Integer money) throws SQLException;

    void myTtransfer(String outUser, String inUser, Integer money) throws SQLException;
}
