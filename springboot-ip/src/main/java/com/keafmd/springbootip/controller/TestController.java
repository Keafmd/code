package com.keafmd.springbootip.controller;

import com.keafmd.springbootip.util.AccessLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Keafmd
 *
 * @ClassName: TestController
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-06-10 14:46
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/one")
    @AccessLimit(seconds = 1, maxCount = 1) //1秒内 允许请求1次
    public String getOne(HttpServletRequest request){
        return "hello";
    }
}
