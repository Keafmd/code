package com.keafmd.springdemo.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
/**
 * Keafmd
 *
 * @ClassName: RestTemplateConfiguration
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-25 9:50
 */
@Configuration
public class RestTemplateConfiguration {

    /**
     * 负载均衡 tem
     *
     * @return
     */

    @Bean
//    @LoadBalanced
    @ConditionalOnProperty(
            value = {"custom-task.loadBalanced"},
            matchIfMissing = false)
    public RestTemplate balanced2Template() {
        return getRestTemplate();
    }


    /**
     * 默认 tem
     *
     * @return
     */
    @Primary
    @Bean
    public RestTemplate normalTemplate() {
        return getRestTemplate();
    }

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
        return restTemplate;
    }
}
