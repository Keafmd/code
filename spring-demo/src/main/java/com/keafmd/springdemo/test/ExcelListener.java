package com.keafmd.springdemo.test;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.keafmd.springdemo.pojo.Api;
import javafx.scene.input.DataFormat;
import lombok.SneakyThrows;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

//创建读取excel监听器
public class ExcelListener extends AnalysisEventListener<Api> {

    //创建list集合封装最终的数据
    List<Api> list = new ArrayList<>();
    //api
    Set<String> apiList = new LinkedHashSet<>();
    //子操作
    Set<String> subOperationList = new LinkedHashSet<>();
    //操作api表
    Set<String> operationApiList = new LinkedHashSet<>();

    //一级二级 api  目录
    Set<String> apiSet = new LinkedHashSet<>();
    Set<String> apiSet2 = new LinkedHashSet<>();

    //操作的二级目录
    Set<String> operationSet = new LinkedHashSet<>();
    Set<String> operationSet2 = new LinkedHashSet<>();


    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String createTime1 = format1.format(new Date());

    String createTime = format1.format(new Date());
    String createTime2 = format1.format(new Date());




    //一行一行去读取excle内容
    @Override
    public void invoke(Api api, AnalysisContext analysisContext) {


        //读取菜单和功能生成api和operation insert语句


        String apiUuid = RandomUtil.randomNumbers(12);
        String apiId = "1513690" + apiUuid;


        //一级api
        String apiSql = "INSERT INTO `ciic_iba_upms`.`upms_api`(`id`, `code`, `name`, `pid`, `path`, `method`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `permission`, `system_id`) VALUES " +
                "("+apiId+", NULL, '"+api.getMenu()+"', -1, NULL, NULL, '"+createTime1+"', '1385053845579603969', NULL, NULL, 0, 2, "+"(SELECT id from (SELECT id FROM `upms_system` where name = '管理端') a)"+");\n";

        if(!apiSet.contains("-- 一级api"+api.getMenu())){
            apiSet.add("-- 一级api"+api.getMenu());
            apiSet.add(apiSql);
        }


        String apiUuid2 = RandomUtil.randomNumbers(12);
        String apiId2 = "1513691" + apiUuid2;
        //二级api
        String apiSql2 = "INSERT INTO `ciic_iba_upms`.`upms_api`(`id`, `code`, `name`, `pid`, `path`, `method`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `permission`, `system_id`) VALUES " +
                "("+apiId2+", NULL, '"+api.getFunction()+"', "+"(SELECT id FROM (SELECT id from `ciic_iba_upms`.`upms_api` WHERE system_id = (SELECT id from (SELECT id FROM `upms_system` where name = '管理端') a) and name = '"+api.getMenu()+"') c )"+", NULL, NULL, '"+createTime1+"', '1385053845579603969', NULL, NULL, 0, 2, "+"(SELECT id from (SELECT id FROM `upms_system` where name = '管理端') b)"+");\n";

        if(!apiSet.contains("-- 二级api"+api.getFunction())){
            apiSet.add("-- 二级api"+api.getFunction());
            apiSet.add(apiSql2);
        }



        //一级operation

        String operationUuid = RandomUtil.randomNumbers(12);
        String operationId = "1513700" + operationUuid;

        String operationSql1 = "INSERT INTO `ciic_iba_upms`.`upms_operation`(`id`, `pid`, `system_id`, `code`, `name`, `description`, `type`, `action`, `sort`, `icon`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`) VALUES " +
                "("+operationId+", -1, (SELECT id from (SELECT id FROM `upms_system` where name = '管理端') a), NULL, '"+api.getMenu()+"', NULL, NULL, NULL, NULL, NULL, '"+createTime1+"', '1385053845579603969', NULL, NULL, 0);\n";

        if(!operationSet.contains("-- 一级operation"+api.getMenu())){
            operationSet.add("-- 一级operation"+api.getMenu());
            operationSet.add(operationSql1);
        }


        //二级operation
        String operationUuid1 = RandomUtil.randomNumbers(12);
        String operationId1 = "1513701" + operationUuid1;
        String operationSql2 = "INSERT INTO `ciic_iba_upms`.`upms_operation`(`id`, `pid`, `system_id`, `code`, `name`, `description`, `type`, `action`, `sort`, `icon`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`) VALUES " +
                "("+operationId1+", (select id from (SELECT id FROM `upms_operation` where `name` = '"+api.getMenu()+"') a), (SELECT id from (SELECT id FROM `upms_system` where name = '管理端') b), NULL, '"+api.getFunction()+"', NULL, NULL, NULL, NULL, NULL, '"+createTime1+"', '1385053845579603969', NULL, NULL, 0);\n";

        if(!operationSet2.contains("-- 二级operation"+api.getFunction())){
            operationSet2.add("-- 二级operation"+api.getFunction());
            operationSet2.add(operationSql2);
        }





        String name = api.getFunctionPoints() + "-" + api.getFunctionPoints();
        String functionName = api.getFunction();

        // function functionPoints
        // menu function
        HashMap<String, Integer> map = new HashMap<>();


        String apiUrl = api.getUrl();
        if (StrUtil.isNotBlank(apiUrl)) {
            apiUrl = apiUrl.replaceAll("[\t\n\r]", "&");
            String[] apiUrls = apiUrl.split("&");

            for (String url : apiUrls) {
                if (StrUtil.isBlank(url)) {
                    continue;
                }
                String uuid = RandomUtil.randomNumbers(12);
                String id = "1513692" + uuid;
                String[] apiStrs = url.split("、");


                try {
                    String requestName = apiStrs[0];
                    String requestMethod = apiStrs[1];
                    String requestPath = apiStrs[2];
                    //当前时间
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



                    //  添加api
                    String sql = "INSERT INTO `ciic_iba_upms`.`upms_api`(" +
                            "`id`, `code`, `name`, `pid`, `path`, `method`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `permission`, `system_id`) VALUES " +
                            "(" + id + ", NULL, '" + requestName + "', (select id from (SELECT id from upms_api where system_id = "+"(SELECT id from (SELECT id FROM `upms_system` where name = '管理端') b)"+" and name = '" + functionName + "') a), '" + requestPath + "', '" + requestMethod + "', '" + createTime + "', '1385053845579603969', NULL, NULL, 0, 2, (SELECT id from (SELECT id FROM `upms_system` where name = '管理端') b));\n";
//                System.out.println(sql);

                    apiList.add(sql);
                } catch (Exception e) {
                    System.out.println(apiStrs.toString());
                    e.printStackTrace();
                }
            }


            //添加操作表数据 - 子操作
            String subOperationName = api.getFunction()+"-"+api.getFunctionPoints();
            String operationName = api.getFunction();

            String uuid = RandomUtil.randomNumbers(12);
            String id = "1513702" + uuid;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String subOptionSql = "INSERT INTO `ciic_iba_upms`.`upms_operation`" +
                    "(`id`, `pid`, `system_id`, `code`, `name`, `description`, `type`, `action`, `sort`, `icon`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`) VALUES " +
                    "("+uuid+", "+"(select id from (SELECT id FROM `upms_operation` where `name` = '"+operationName+"') a)"+", (SELECT id from (SELECT id FROM `upms_system` where name = '管理端') b), NULL, '"+subOperationName+"', NULL, NULL, NULL, NULL, NULL, '"+createTime+"', '1385053845579603969', NULL, NULL, 0);\n";

            subOperationList.add(subOptionSql);
//            System.out.println(subOptionSql);


            //添加操作api表
            for (String url : apiUrls) {
                if (StrUtil.isBlank(url)) {
                    continue;
                }
                String uuid1 = RandomUtil.randomNumbers(12);
                String operationApiId = "1386594" + uuid1;
                String[] apiStrs = url.split("、");


                String requestName = apiStrs[0];
                String requestMethod = apiStrs[1];
                String requestPath = apiStrs[2];
                //当前时间
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



                //  添加api
                String operationApiSql =  "INSERT INTO `ciic_iba_upms`.`upms_operation_api`" +
                        "(`id`, `operation_id`, `api_id`, `create_time`) VALUES " +
                        "("+operationApiId+", "+"(SELECT id FROM (SELECT id FROM `upms_operation` WHERE name = '"+subOperationName+"') a)"+", "+"(SELECT id FROM (SELECT id FROM `upms_api` WHERE name = '"+requestName+"') b LIMIT 0 ,1 )"+", '"+createTime2+"');\n";
                operationApiList.add(operationApiSql);
            }




        }




        list.add(api);
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息：" + headMap);
    }

    //读取完成后执行
    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

//        System.out.println(apiSet.toArray()[0]);
//        System.out.println(apiSet2.toArray()[0]);
//        System.out.println(operationSet.toArray()[0]);
//        System.out.println(operationSet2.toArray()[0]);

        PrintStream Out = System.out;//创建一个打印输出流，输出的目标是E下的练习写入文件.txt

        PrintStream write = new PrintStream("C:\\Users\\章贺龙\\Desktop\\api.sql");

        System.setOut(write);//把创建的打印输出流赋给系统。即系统下次想write输出
        //打印
        System.out.println("--  api表和操作表目录");
        apiSet.forEach(System.out::println);
        apiSet2.forEach(System.out::println);
        operationSet.forEach(System.out::println);
        operationSet2.forEach(System.out::println);


        System.out.println("--  api表和操作表数据");
        apiList.forEach(System.out::println);
        subOperationList.forEach(System.out::println);

        System.out.println("--  操作api表数据");
        operationApiList.forEach(System.out::println);
    }
}