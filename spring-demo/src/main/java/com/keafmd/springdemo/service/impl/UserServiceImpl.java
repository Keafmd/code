package com.keafmd.springdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.keafmd.springdemo.handle.SingleThreadConnectionHolder;
import com.keafmd.springdemo.handle.TransactionManager;
import com.keafmd.springdemo.mapper.UserMapper;
import com.keafmd.springdemo.pojo.User;
import com.keafmd.springdemo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Keafmd
 *
 * @ClassName: UserServiceImpl
 * @Description: 调用的不是同一个Connection对象
 * @author: 牛哄哄的柯南
 * @date: 2022-04-07 11:47
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    private final DataSource dataSource;

    private TransactionManager transactionManager;

    public UserServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        transactionManager = new TransactionManager(dataSource);
    }

    @Override
    public void transferError(String outUser, String inUser, Integer money) throws SQLException {

        Connection connection = dataSource.getConnection();
        //关闭自动提交
        connection.setAutoCommit(false);

        try{
            //执行转账操作
            //1.减少转出人的余额
            User userO = baseMapper.selectById(outUser);
            userO.setCount(userO.getCount() - money);
            baseMapper.updateById(userO);
            //2.增加转入人的余额
            User userI = baseMapper.selectById(inUser);
            userI.setCount(userI.getCount() + money);
            baseMapper.updateById(userI);
            //提交事务
            connection.commit();
        }catch (Exception e) {
            try{
                assert connection != null;
                connection.rollback();
            }catch (SQLException e1){
                log.error("事务回滚失败",e1);
                e1.printStackTrace();
            }

        }finally {
            try {
                assert connection != null;
                connection.close();
            }catch (SQLException e) {
                log.error("关闭连接失败",e);
                e.printStackTrace();
            }

        }


    }

    @Override
    public void transfer(String outUser, String inUser, Integer money) throws SQLException {

//        Connection connection = dataSource.getConnection();
        Connection connection = SingleThreadConnectionHolder.getConnection(dataSource);
        //关闭自动提交
        connection.setAutoCommit(false);

        try{
            //执行转账操作
            //1.减少转出人的余额
            User userO = baseMapper.selectById(outUser);
            userO.setCount(userO.getCount() - money);
            String[] s = new String[2];
            s[0] = money.toString();
            s[1] = outUser;
            connection.prepareStatement("update user set count = count - s[0] where id = s[1]",s);
//            baseMapper.updateById(userO);
            //2.增加转入人的余额
            User userI = baseMapper.selectById(inUser);
            userI.setCount(userI.getCount() + money);
            s[0] = money.toString();
            s[1] = inUser;
//            baseMapper.updateById(userI);
            connection.prepareStatement("update user set count = count - s[0] where id = s[1]",s);

            //提交事务
            connection.commit();
        }catch (Exception e) {
            try{
                assert connection != null;
                connection.rollback();
            }catch (SQLException e1){
                log.error("事务回滚失败",e1);
                e1.printStackTrace();
            }

        }finally {
            try {
                assert connection != null;
                connection.close();
            }catch (SQLException e) {
                log.error("关闭连接失败",e);
                e.printStackTrace();
            }

        }


    }

    @Override
    public void transferEasy(String outUser, String inUser, Integer money) throws SQLException {
        try
        {
            transactionManager.start();

            //执行转账操作
            //1.减少转出人的余额
            User userO = baseMapper.selectById(outUser);
            userO.setCount(userO.getCount() - money);
            baseMapper.updateById(userO);
            //2.增加转入人的余额
            User userI = baseMapper.selectById(inUser);
            userI.setCount(userI.getCount() + money);
            baseMapper.updateById(userI);


            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void myTtransfer(String outUser, String inUser, Integer money) throws SQLException {
        //执行转账操作
        //1.减少转出人的余额
        User userO = baseMapper.selectById(outUser);
        userO.setCount(userO.getCount() - money);
        baseMapper.updateById(userO);
        //2.增加转入人的余额
        User userI = baseMapper.selectById(inUser);
        userI.setCount(userI.getCount() + money);
        baseMapper.updateById(userI);
    }

}
