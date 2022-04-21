package com.keafmd.springdemo.test;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;


/**
 * Keafmd
 *
 * @ClassName: EnterpriseDemo
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-21 10:54
 */
public class EnterpriseDemo {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        // token可以从 数据中心 -> 我的接口 中获取
        String token = "您的token";
        String url = "http://open.api.tianyancha.com/****/***/**/****/normal?keyword=中航重机股份有限公司";
        System.out.println(getMessageByUrlToken(url, token));
    }

    /**
     *
     * @return
     */
    public static String getMessageByUrlToken(String url,String token){
        String result="";
        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(url);//这⾥发送get请求
            // 设置请求头信息
            request.setHeader("Authorization", token);//这里我定义的token定义的全局变量
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);
            // 判断⽹络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block e.printStackTrace();
            e.printStackTrace();
        }
        return result;
    }


}

