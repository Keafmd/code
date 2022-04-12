package com.keafmd.springdemo.test;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.keafmd.springdemo.pojo.Api;
import javafx.scene.input.DataFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

//创建读取excel监听器
public class ExcelListener extends AnalysisEventListener<Api> {
 
    //创建list集合封装最终的数据
    List<Api> list = new ArrayList<>();
 
    //一行一行去读取excle内容
    @Override
    public void invoke(Api api, AnalysisContext analysisContext) {

        String name = api.getFunctionPoints()+"-"+api.getFunctionPoints();
        String functionName = api.getFunction();

        // function functionPoints
        // menu function
        HashMap<String,Integer> map = new HashMap<>();

        String apiUrl = api.getUrl();
        if(StrUtil.isNotBlank(apiUrl)) {
            apiUrl = apiUrl.replaceAll("[\t\n\r]","");
            String[] apiUrls = apiUrl.split("&");

        for (String url : apiUrls) {
            String uuid = RandomUtil.randomNumbers(12);
            String id  = "1513692"+uuid;
            String[] apiStrs = url.split("、");


            String requestName = apiStrs[0];
            String requestMethod = apiStrs[1];
            String requestPath = apiStrs[2];
            //当前时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = format.format(new Date());



            //  添加api
            String sql = "INSERT INTO `ciic_iba_upms`.`upms_api`(" +
                    "`id`, `code`, `name`, `pid`, `path`, `method`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `permission`, `system_id`) VALUES " +
                    "("+id+", NULL, '"+requestName+"', (select id from (SELECT id from upms_api where system_id = '1513360236112338945' and name = '"+functionName+"') a), '"+requestPath+"', '"+requestMethod+"', '"+createTime+"', '1385053845579603969', NULL, NULL, 0, 2, 1513360236112338945);\n";
            System.out.println(sql);
        }

        }






       list.add(api);
    }
 
    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }
 
    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}