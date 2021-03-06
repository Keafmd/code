package com.keafmd.springdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keafmd.springdemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Keafmd
 *
 * @ClassName: UserMapper
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-07 11:49
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
