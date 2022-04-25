package com.keafmd.springdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keafmd.springdemo.handle.SingleThreadConnectionHolder;
import com.keafmd.springdemo.handle.TransactionManager;
import com.keafmd.springdemo.mapper.UserMapper;
import com.keafmd.springdemo.pojo.User;
import com.keafmd.springdemo.service.IUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    private final TransactionManager transactionManager;

    public UserServiceImpl(DataSource dataSource, ObjectMapper objectMapper, RedisTemplate<String, String> redisTemplate) {
        this.dataSource = dataSource;
        transactionManager = new TransactionManager(dataSource);
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
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


    private static final String USER_KEY = "USER_KEY";

    private final ObjectMapper objectMapper;

    private final RedisTemplate<String, String> redisTemplate;


    @Override
    @SneakyThrows
    public User getUserById(String userId) {

        Map<String,User> userMap = new HashMap<>();
        if (Boolean.FALSE.equals(redisTemplate.hasKey(USER_KEY))) {
            List<User> users = list();
            userMap = users.stream().collect(Collectors.toMap(i->i.getId(),user->user));
            String valueAsString = objectMapper.writeValueAsString(userMap);
            //通过ValueOperations设置值
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            //48小时
            ops.set(USER_KEY, valueAsString, 48, TimeUnit.HOURS);
        }else{
            //通过ValueOperations获取值
            String val = redisTemplate.opsForValue().get(USER_KEY);
            JavaType javaType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, User.class);
            userMap = objectMapper.readValue(val, javaType);
        }
        return userMap.get(userId);
    }

}
