package com.keafmd.springdemo.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Keafmd
 *
 * @ClassName: user
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-07 11:39
 */
@Data
@Accessors(chain = true)
public class User {

    private String id;

    private String name;

    private String num;

    private Integer count;

}
