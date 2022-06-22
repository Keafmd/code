package com.keafmd.springdemo.dataMigration;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Keafmd
 *
 * @ClassName: DataJdbc
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-05-16 11:59
 */
public class DataJdbc {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    PrintStream write = new PrintStream("C:\\Users\\章贺龙\\Desktop\\upmsUserInsert.sql");
//    PrintStream write = new PrintStream("\\upmsUserInsert.sql");



    Connection connection;
    List<CommonUserAndBizStudent> userList = new ArrayList<>();
    List<CommonUserAndBizCompany> companyList = new ArrayList<>();
    // 预才网 upms
    Map<String,String> userMap = new HashMap<>();
    Map<String,String> orgMap = new HashMap<>();
    Map<String,String> constantMap = new HashMap<>();
    Map<String,String> CompanyPassMap = new HashMap<>();


    Set<String> upmsUserStudentSql = new HashSet<>();
    Set<String> upmsUserCompanySql = new HashSet<>();
    Set<String> upmsMemberSql = new HashSet<>();
    Set<String> upmsTenantSql = new HashSet<>();
    Set<String> upmsOrgSql = new HashSet<>();
    Set<String> upmsAgencySql = new HashSet<>();
    Set<String> upmsPassSql = new HashSet<>();
    Set<String> upmsUserPassSql = new HashSet<>();
    Set<String> upmsInfoSql = new HashSet<>();
    Set<String> accountAndPass = new HashSet<>();
    Set<String> exceptionSql = new HashSet<>();

    public DataJdbc() throws FileNotFoundException {
    }

    public void init() throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dburl = "jdbc:sqlserver://**:**;DatabaseName=master";
        String user = "**";
        String password = "****";
        try {
            connection = java.sql.DriverManager.getConnection(dburl, user, password);
            System.out.println(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 执行查询返回结果集
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }

    /**
     * 通过文件服务把url转为文件id
     * @param url
     * @return
     */
    public String getFileIdByUrl(String url){
        String result="";
        try {
            // 根据地址获取请求
            HttpPost request = new HttpPost("http://zpw-org-develop.ciicit.com.cn:31567/api/ciic-iba-file-service/file/url?url="+url+"&filename=test.png");//这⾥发送get请求
            // 设置请求头信息
//            request.setHeader("Authorization", token);//这里我定义的token定义的全局变量
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);
            // 判断⽹络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
                JSONObject res = JSONObject.parseObject(result);
                JSONObject data = (JSONObject) res.get("data");
                result = data.get("id").toString();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block e.printStackTrace();
            e.printStackTrace();
            result = "";
            return result;

        }
        return result;
    }

    public Address getAddress(String key){
        if(StrUtil.isBlank(key)){
            return null;
        }

        key =  key.replaceAll(" ","");
        Address address = new Address();

        AddressSearchDTO dto = new AddressSearchDTO();
        dto.setKeyword(key);
        dto.setBoundary("nearby(40.040589,116.273543,1000)");
        dto.setPageSize("5");
        dto.setPageIndex("1");

        try {
            // 根据地址获取请求

            HttpPost request = new HttpPost("http://zpw-org-develop.ciicit.com.cn:31567/api/ciic-iba-system-service/map");//这⾥发送get请求
//            HttpPost request = new HttpPost("http://localhost:7012/map");//这⾥发送get请求
            // 设置请求头信息

            request.setHeader("Accept","application/json");
            request.setHeader("Content-Type","application/json;charset=UTF-8");

            String jsonstr = JSON.toJSONString(dto);
            StringEntity se = new StringEntity(jsonstr);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            request.setEntity(se);

//            request.setHeader("Authorization", token);//这里我定义的token定义的全局变量
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);
            // 判断⽹络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result= EntityUtils.toString(response.getEntity(),"utf-8");
                JSONObject res = JSONObject.parseObject(result);
                String data = res.get("data").toString();
                List<Address> addresses = JSONArray.parseArray(data, Address.class);
                Address add = new Address();
                if(addresses.size()>0){
                    add = addresses.get(0);
                    return add;
                }else{
                    return null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return null;

    }

    private String getAreaId(String area) {
        if(StrUtil.isBlank(area)){
            return "";
        }
        String result="";
        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet("http://zpw-org-develop.ciicit.com.cn:31567/api/ciic-iba-system-service/area/id/"+area);//这⾥发送get请求
//            HttpGet request = new HttpGet("http://localhost:7012/area/id/"+area);//这⾥发送get请求
            // 设置请求头信息
//            request.setHeader("Authorization", token);//这里我定义的token定义的全局变量
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);
            // 判断⽹络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
                JSONObject res = JSONObject.parseObject(result);
                String data = (String)res.get("data");
                result = data;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block e.printStackTrace();
            e.printStackTrace();
            result = "";
            return result;

        }
        return result;
    }


    public Integer commonUserAndBizStudentCount(){
        Integer num = 0;
        try {
            ResultSet resultSet = executeQuery("select  count(*) as num from ciicsqldev.dbo.Common_User a LEFT JOIN ciicsqldev.dbo.Biz_Student b on a.UserId = b.UserId WHERE a.UserType = 1 ");
            // 获取ResultSet对象的列的数量、类型和属性。
            ResultSetMetaData md= resultSet.getMetaData();
            // 获取列的数量
            int columnCount = md.getColumnCount();
            // 将ResultSet对象的列名和值存到map中，再将map转换为json字符串，最后将json字符串转换为实体类对象
            Map<String, Object> rowData = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    if(rowData.containsKey(md.getColumnLabel(i))){
                        rowData.put(md.getColumnLabel(i)+"2", resultSet.getObject(i));
                    }
                    rowData.put(md.getColumnLabel(i), resultSet.getObject(i));
                }

                num = (Integer)rowData.get("num");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;

    }
    public Integer commonUserAndBizCompanyCount(){
        Integer num = 0;
        try {
            ResultSet resultSet = executeQuery("select  count(*) as num  from ciicsqldev.dbo.Common_User a LEFT JOIN ciicsqldev.dbo.Biz_Company b on a.UserId = b.UserId WHERE a.UserType = 2 and a.SourceId is null ");
            // 获取ResultSet对象的列的数量、类型和属性。
            ResultSetMetaData md= resultSet.getMetaData();
            // 获取列的数量
            int columnCount = md.getColumnCount();
            // 将ResultSet对象的列名和值存到map中，再将map转换为json字符串，最后将json字符串转换为实体类对象
            Map<String, Object> rowData = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    if(rowData.containsKey(md.getColumnLabel(i))){
                        rowData.put(md.getColumnLabel(i)+"2", resultSet.getObject(i));
                    }
                    rowData.put(md.getColumnLabel(i), resultSet.getObject(i));
                }
                num = (Integer) rowData.get("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;

    }


    /**
     * 获取CommonUserAndBizStudent
     */
    public void toCommonUserAndBizStudent(String pageIndex,String pageSize){
        try {
            ResultSet resultSet = executeQuery("select *  from ciicsqldev.dbo.Common_User a LEFT JOIN ciicsqldev.dbo.Biz_Student b on a.UserId = b.UserId WHERE a.UserType = 1 order by a.UserId offset ("+pageIndex+"-1)*"+pageSize+" rows fetch next "+pageSize+" rows only ");
            // 获取ResultSet对象的列的数量、类型和属性。
            ResultSetMetaData md= resultSet.getMetaData();
            // 获取列的数量
            int columnCount = md.getColumnCount();
            // 将ResultSet对象的列名和值存到map中，再将map转换为json字符串，最后将json字符串转换为实体类对象
            Map<String, Object> rowData = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    if(rowData.containsKey(md.getColumnLabel(i))){
                        rowData.put(md.getColumnLabel(i)+"2", resultSet.getObject(i));
                    }
                    rowData.put(md.getColumnLabel(i), resultSet.getObject(i));
                }
                String jsonStr = JSONObject.toJSONString(rowData);
                CommonUserAndBizStudent commonUser = JSONObject.parseObject(jsonStr, CommonUserAndBizStudent.class);
                userList.add(commonUser);
            }
//            CommonUserAndBizStudent commonUser = userList.get(2);
//            System.out.println(commonUser);
//            System.out.println(passwordEncoder.encode(commonUser.getEmail()));
//            System.out.println(passwordEncoder.encode("1"));
//            System.out.println(passwordEncoder.matches("1", "$2a$10$OZgQXdoU49Dz1zjsCUvBwOa7m8Pu6HEHrvZ/yJiXKSeoxsRuTZeaO"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取CommonUserAndBizCompany
     */
    public void toCommonUserAndBizCompany(String pageIndex,String pageSize){
        try {
            ResultSet resultSet = executeQuery("select   *   from ciicsqldev.dbo.Common_User a LEFT JOIN ciicsqldev.dbo.Biz_Company b on a.UserId = b.UserId WHERE a.UserType = 2 and a.SourceId is null order by b.CompanyId offset ("+pageIndex+"-1)*"+pageSize+" rows fetch next "+pageSize+" rows only");
//            ResultSet resultSet = executeQuery("select top 1000 a.userId,a.RelationId ,b.* from ciicsqldev.dbo.Common_User a LEFT JOIN ciicsqldev.dbo.Biz_Company b on a.UserId = b.UserId WHERE a.UserType = 2 and a.RelationId = '80A7456D-627C-4E3D-B0DB-CF87BF07FDBE'");
//             获取ResultSet对象的列的数量、类型和属性。
            ResultSetMetaData md= resultSet.getMetaData();
            // 获取列的数量
            int columnCount = md.getColumnCount();
            // 将ResultSet对象的列名和值存到map中，再将map转换为json字符串，最后将json字符串转换为实体类对象
            Map<String, Object> rowData = new HashMap<>();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    if(rowData.containsKey(md.getColumnLabel(i))){
                        rowData.put(md.getColumnLabel(i)+"2", resultSet.getObject(i));
                    }
                    rowData.put(md.getColumnLabel(i), resultSet.getObject(i));
                }
                String jsonStr = JSONObject.toJSONString(rowData);
                CommonUserAndBizCompany commonUser = JSONObject.parseObject(jsonStr, CommonUserAndBizCompany.class);
                companyList.add(commonUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * CommonUser2upmsUser sql
     * 密码为随机16位字符串
     * 邮箱作为密码的pwd_init_flag都是0，随机生成的密码pwd_init_flag都是1。
     * join_org_count 直接删了吧
     *
     */
    public void CommonUserAndBizStudent2upmsUser() throws FileNotFoundException {
//        upmsUserSql = new HashSet<>();
        String password = null;
        String sql;
        String id;
        String createTime = null;
        String updateTime = DateUtil.formatDateTime(new Date());
        String birthday = null;
        String pwdInitFlag = "1"; //随机生成的密码
        String authStatus = "1"; //学生端为未认证
        int snum = 0;
        for (CommonUserAndBizStudent entity : userList) {
            System.out.println("正在处理学生数据["+snum+"]："+entity.getUsername());
            snum++;
            String pas = RandomUtil.randomString(16);
//            password = passwordEncoder.encode(pas);
            password = pas;
//            if(StrUtil.isNotBlank(entity.getEmail())){
//                password = passwordEncoder.encode(entity.getEmail());
//            }

            String ap = "-- 账号："+entity.getUsername() + "密码："+ pas;
            accountAndPass.add(ap);
            if(StrUtil.isNotBlank(entity.getBirthday())){
                birthday = DateUtil.formatDateTime(new Timestamp(Long.parseLong(entity.getBirthday())));
            }
            if(StrUtil.isNotBlank(entity.getCreateTime())){
                createTime = DateUtil.formatDateTime(new Timestamp(Long.parseLong(entity.getCreateTime())));
            }
            id = "166198765" + RandomUtil.randomNumbers(10);
            sql = "INSERT INTO `ciic_iba_upms`.`upms_user`" +
                    "(`id`, `name`, `account`, `password`, `salt`, `locked`, `phone`, `avatar`, `email`, `gender`, `birthday`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `pwd_init_flag`, `lock_desc`, `last_login_time`, `join_org_count`, `auth_status`, `disable_time`) VALUES " +
                     "("+id+", '"+entity.getUsername()+"', '"+entity.getUsername()+"', '"+password+"', NULL, 0, '"+entity.getMobilePhone()+"', NULL, '"+entity.getEmail()+"', "+constantMap.get(entity.getGender())+", '"+birthday +"', '"+createTime+"', '1', '"+updateTime+"', '1', 0, "+pwdInitFlag+", NULL, NULL, NULL, "+authStatus+", NULL);";
            upmsUserStudentSql.add(sql);

            String userpassid = "166198764" + RandomUtil.randomNumbers(10);
            String upmsuserpassSql = "INSERT INTO `upms_user_pass`(`id`, `user_id`, `pass_id`, `create_time`, `update_time`, `status`) VALUES " +
                    "("+userpassid+", "+id+", '6', '"+createTime+"', '"+updateTime+"', NULL);";

            upmsUserPassSql.add(upmsuserpassSql);

            String infoId = "166198774" + RandomUtil.randomNumbers(10);
            String val = null;
            if(StrUtil.isNotBlank(entity.getGraduateTime())){
                val = DateUtil.formatDateTime(new Timestamp(Long.parseLong(entity.getGraduateTime())));
            }
            String info = "INSERT INTO `upms_user_expand_info`(`id`, `user_id`, `group_id`, `type`, `value`) VALUES ("+infoId+", "+id+", '1', '1', '"+val+"');";
            upmsInfoSql.add(info);

        }

//        PrintStream write = new PrintStream("C:\\Users\\章贺龙\\Desktop\\upmsUserInsert.sql");

//        System.setOut(write);//把创建的打印输出流赋给系统。即系统下次想write输出
//        //打印
//        System.out.println("--  upmsUserInser 语句");
//        System.out.println("--  学生开始");
//        upmsUserSql.forEach(System.out::println);
//        System.out.println("--  学生结束");

    }


    /**
     * CommonUserAndBizCompany2upmsUser
     */
    public void CommonUserAndBizCompany2upmsUser() throws Exception {

        //企业用户如有没有邮箱就把密码设置成123456

        String password = passwordEncoder.encode("123456");
        String pas = "123456";
        String sql;
        String id;
        String createTime = null;
        String updateTime = DateUtil.formatDateTime(new Date());
        String setUpTime = DateUtil.formatDateTime(new Date());
        String pwdInitFlag = "1";
        String authStatus = "0";//企业端为已认证
        String logo = "NULL";
        String license = "NULL";
        String disableTime = null;
        // 排序下 根据CompanyId，管理员在上面 ，null 在下面
        companyList.sort(Comparator.comparing(CommonUserAndBizCompany::getCompanyId, Comparator.nullsLast(String::compareTo)));

        int cnum=0;
        for (CommonUserAndBizCompany entity : companyList) {
            System.out.println("正在处理公司数据["+cnum+"]："+entity.getUsername());
            cnum++;
            createTime = null;

            if(StrUtil.isNotBlank(entity.getEmail())){
                password = passwordEncoder.encode(entity.getEmail());
                pas = entity.getEmail();
                pwdInitFlag = "0";
            }
            if(StrUtil.isNotBlank(entity.getCreateTime())){
                createTime = DateUtil.formatDateTime(new Timestamp(Long.parseLong(entity.getCreateTime())));
            }
            if(StrUtil.isNotBlank(entity.getSetupTime())){
                setUpTime = DateUtil.formatDateTime(new Timestamp(Long.parseLong(entity.getSetupTime())));
            }
            if(createTime == null){
                createTime = DateUtil.formatDateTime(new Timestamp(System.currentTimeMillis()));
            }
            if(createTime == null){
                createTime = "NULL";
            }else{
                createTime = "'"+createTime+"'";
            }

            String ap = "-- 企业账号："+entity.getUsername() + "密码："+ pas;
            accountAndPass.add(ap);

            id = "166198766" + RandomUtil.randomNumbers(10);
            sql = "INSERT INTO `ciic_iba_upms`.`upms_user`" +
                    "(`id`, `name`, `account`, `password`, `salt`, `locked`, `phone`, `avatar`, `email`, `gender`, `birthday`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `pwd_init_flag`, `lock_desc`, `last_login_time`, `join_org_count`, `auth_status`, `disable_time`) VALUES " +
                    "("+id+", '"+entity.getUsername()+"', '"+entity.getUsername()+"', '"+password+"', NULL, 0, '"+entity.getMobilePhone()+"', NULL, '"+entity.getEmail()+"', NULL, NULL, "+createTime+", '1', '"+updateTime+"', '1', 0, "+pwdInitFlag+", NULL, NULL, NULL, "+authStatus+", NULL);";
            upmsUserCompanySql.add(sql);

            String userpassid = "166198773" + RandomUtil.randomNumbers(10);
            String upmsuserpassSql2 = "INSERT INTO `upms_user_pass`(`id`, `user_id`, `pass_id`, `create_time`, `update_time`, `status`) VALUES " +
                    "("+userpassid+", "+id+", '6', "+createTime+", '"+updateTime+"', NULL);";

            upmsUserPassSql.add(upmsuserpassSql2);


            //添加一个企业用户 关联了企业
            if(StrUtil.isNotBlank(entity.getCompanyId())) {




                String tenantId =  "166198767" + RandomUtil.randomNumbers(10);
                String upmstenantSql = "INSERT INTO `upms_tenant`(`id`, `code`, `name`, `domain_prefix`, `domain`, `type`, `open_time`, `expire_time`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`) VALUES " +
                        "(" + tenantId + ", '"+entity.getCompanyNo()+"', '"+entity.getCompanyName2()+"', '1', '1', 1, '2021-04-01 14:30:51', '2041-04-01 14:30:54', "+createTime+", '1', '"+updateTime+"', '1', 0);";
                upmsTenantSql.add(upmstenantSql);

                String agencyId = "166198768" + RandomUtil.randomNumbers(10);
                String upmsagencySql = "INSERT INTO `upms_agency`(`id`, `create_time`, `update_time`, `name`, `pid`, `sort`, `tree_id`, `tenant_id`, `create_by`, `update_by`, `code` )VALUES " +
                        "("+agencyId+", "+createTime+", '"+updateTime+"', '"+entity.getCompanyName()+"', -1, NULL, '1000', "+tenantId+", '1', '1', NULL);";
                upmsAgencySql.add(upmsagencySql);


                String memberId =  "166198769" + RandomUtil.randomNumbers(10);
                String upmsmemberSql = "INSERT INTO `upms_member`(`id`, `tenant_id`, `user_id`, `status`, `locked`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `agency_id`, `name`, `gender`, `phone`, `email`, `another_name`, `birthday`, `code`, `invitation_id`, `post`, `other`, `channel`)VALUES " +
                        "("+memberId+", "+tenantId+", "+id+", NULL, 0, "+createTime+", '1', '"+updateTime+"', '1', 0, "+agencyId+", '"+entity.getUsername()+"', NULL, '"+entity.getMobilePhone()+"', '"+entity.getEmail()+"', NULL, NULL, NULL, NULL, NULL, NULL, NULL);";
                upmsMemberSql.add(upmsmemberSql);

                if(StrUtil.isNotBlank(entity.getLogoImage())){
                    logo = getFileIdByUrl(entity.getLogoImage());
                }
                if(StrUtil.isNotBlank(entity.getBusinessLicense())){
                    license = getFileIdByUrl(entity.getBusinessLicense());
                }
                if(StrUtil.isNotBlank(entity.getBlockTime())){
                    disableTime = DateUtil.formatDateTime(new Timestamp(Long.parseLong(entity.getBlockTime())));
                }

                // type ：'"+entity.getCompanyType()+"'
                //'"+entity.getIndustry()+"' 111

                String types = constantMap.getOrDefault(entity.getCompanyType(),"");
                Integer type = null;
                if(!"".equals(types)){
                    type = Integer.parseInt(types);
                }
                String industrys = constantMap.getOrDefault(entity.getIndustry(),"");
//                String orgId = "166198770" + RandomUtil.randomNumbers(10);
                Integer industry = null;
                if(!"".equals(industrys)){
                    industry= Integer.parseInt(industrys);
                }
                String orgId = tenantId;
                //entity.getAuditStatus()
                String ststus = entity.getAuditStatus();
                String ststus2 = entity.getAuditStatus();
                if(ststus!=null){
                    ststus2 = constantMap.get(ststus);
                }else{
                    ststus2 = "310";
                }
                String upmsorgSql = "";

                if("".equals(logo)){
                   logo = "NULL";
                }
                if("".equals(license)){
                    license = "NULL";
                }

                String area = "";
                String area_id = "";
                String street = "";
                String doorplate = "";
                String lat = "";
                String lng = "";

                Address address = getAddress(entity.getAddress());
                if(address != null){

                    lat = "'"+address.getLat()+"'";
                    lng = "'"+address.getLng()+"'";
                    String province = address.getProvince().contains("市")?address.getProvince().replace("市",""):address.getProvince();
                    String city = address.getCity();
                    String district = address.getDistrict();
                    if(province.contains("特别行政区")){
                        province = province.replace("特别行政区","");
                        city = province;
                    }
                    area = "'"+district+"'";

                    area_id = getAreaId(city+","+district);

                }

                if("".equals(area)){
                    area = "NULL";
                }
                if("".equals(area_id)){
                    area_id = "NULL";
                }
                if("".equals(street)){
                    street = "NULL";
                }
                if("".equals(doorplate)){
                    doorplate = "NULL";
                }
                if("".equals(lat)){
                    lat = "NULL";
                }
                if("".equals(lng)){
                    lng = "NULL";
                }

                String description = "";
                description = entity.getIntroduction();
                if("".equals(description)){
                    description = "NULL";
                }else{
                    description = "'"+description+"'";
                }

                //企业规模
                String scale = "";
                scale = constantMap.getOrDefault(entity.getEmployeeCount(),"");
                if("".equals(scale)){
                    scale = "NULL";
                }else{
                    scale = "'"+scale+"'";
                }


                if(disableTime==null&&type == null){
                    upmsorgSql = "INSERT INTO `upms_org`(`id`, `parent_id`, `group_id`, `create_time`, `update_time`, `credit_code`, `name`, `abbreviation`, `type`, `registered_address`, `office_address`, `establishment_date`, `area`, `area_id`, `street`, `doorplate`, `lat`, `lng`, `representative`, `create_by`, `update_by`, `domain`, `logo`, `licenses`, `status`, `status_desc`, `description`, `photos`, `videos`, `official_website`, `resource`, `industry`, `progress_phase`, `scale`, `address`, `disable_time`, `contact_phone`, `contact_name`, `contact_email`, `sort_num`,`contact_post`) VALUES " +
                            "(" + orgId + ", NULL, NULL, " + createTime + ", '" + updateTime + "', '" + entity.getOrgCode() + "', '" + entity.getCompanyName2() + "', '" + entity.getCompanyShortName() + "', NULL, '', '', '" + setUpTime + "', "+area+", "+area_id+", "+street+", "+doorplate+", "+lat+", "+lng+", '" + entity.getCorporateRepresentative() + "', 1, 1, NULL, " + logo + ", " + license + ", '" + ststus2 + "', '" + entity.getAuditRemark() + "',"+description+", NULL, NULL, '" + entity.getWebsite() + "', NULL, "+industry+", NULL, "+scale+", '" + entity.getAddress() + "', NULL , '" + entity.getContactPhone() + "', '" + entity.getContactName() + "', '" + entity.getContactEmail() + "', -1,'" + entity.getContactPosition() + "');";

                }else if(disableTime!=null&&type==null){
                    upmsorgSql = "INSERT INTO `upms_org`(`id`, `parent_id`, `group_id`, `create_time`, `update_time`, `credit_code`, `name`, `abbreviation`, `type`, `registered_address`, `office_address`, `establishment_date`, `area`, `area_id`, `street`, `doorplate`, `lat`, `lng`, `representative`, `create_by`, `update_by`, `domain`, `logo`, `licenses`, `status`, `status_desc`, `description`, `photos`, `videos`, `official_website`, `resource`, `industry`, `progress_phase`, `scale`, `address`, `disable_time`, `contact_phone`, `contact_name`, `contact_email`, `sort_num`,`contact_post`) VALUES " +
                            "(" + orgId + ", NULL, NULL, " + createTime + ", '" + updateTime + "', '" + entity.getOrgCode() + "', '" + entity.getCompanyName2() + "', '" + entity.getCompanyShortName() + "', NULL, '', '', '" + setUpTime + "', "+area+", "+area_id+", "+street+", "+doorplate+", "+lat+", "+lng+", '" + entity.getCorporateRepresentative() + "', 1, 1, NULL, " + logo + ", " + license + ", '" + ststus2 + "', '" + entity.getAuditRemark() + "',"+description+", NULL, NULL, '" + entity.getWebsite() + "', NULL, "+industry+", NULL, "+scale+", '" + entity.getAddress() + "', '" + disableTime + "', '" + entity.getContactPhone() + "', '" + entity.getContactName() + "', '" + entity.getContactEmail() + "', -1,'" + entity.getContactPosition() + "');";
                }else if(disableTime==null&&type!=null){
                    upmsorgSql = "INSERT INTO `upms_org`(`id`, `parent_id`, `group_id`, `create_time`, `update_time`, `credit_code`, `name`, `abbreviation`, `type`, `registered_address`, `office_address`, `establishment_date`, `area`, `area_id`, `street`, `doorplate`, `lat`, `lng`, `representative`, `create_by`, `update_by`, `domain`, `logo`, `licenses`, `status`, `status_desc`, `description`, `photos`, `videos`, `official_website`, `resource`, `industry`, `progress_phase`, `scale`, `address`, `disable_time`, `contact_phone`, `contact_name`, `contact_email`, `sort_num`,`contact_post`) VALUES " +
                            "(" + orgId + ", NULL, NULL, " + createTime + ", '" + updateTime + "', '" + entity.getOrgCode() + "', '" + entity.getCompanyName2() + "', '" + entity.getCompanyShortName() + "', '"+type+"', '', '', '" + setUpTime + "',"+area+", "+area_id+", "+street+", "+doorplate+", "+lat+", "+lng+", '" + entity.getCorporateRepresentative() + "', 1, 1, NULL, " + logo + ", " + license + ", '" + ststus2 + "', '" + entity.getAuditRemark() + "',"+description+", NULL, NULL, '" + entity.getWebsite() + "', NULL, "+industry+", NULL, "+scale+", '" + entity.getAddress() + "', NULL, '" + entity.getContactPhone() + "', '" + entity.getContactName() + "', '" + entity.getContactEmail() + "', -1,'" + entity.getContactPosition() + "');";
                }else{
                    upmsorgSql = "INSERT INTO `upms_org`(`id`, `parent_id`, `group_id`, `create_time`, `update_time`, `credit_code`, `name`, `abbreviation`, `type`, `registered_address`, `office_address`, `establishment_date`, `area`, `area_id`, `street`, `doorplate`, `lat`, `lng`, `representative`, `create_by`, `update_by`, `domain`, `logo`, `licenses`, `status`, `status_desc`, `description`, `photos`, `videos`, `official_website`, `resource`, `industry`, `progress_phase`, `scale`, `address`, `disable_time`, `contact_phone`, `contact_name`, `contact_email`, `sort_num`,`contact_post`) VALUES " +
                            "(" + orgId + ", NULL, NULL, " + createTime + ", '" + updateTime + "', '" + entity.getOrgCode() + "', '" + entity.getCompanyName2() + "', '" + entity.getCompanyShortName() + "', '"+type+"', '', '', '" + setUpTime + "',"+area+", "+area_id+", "+street+", "+doorplate+", "+lat+", "+lng+", '" + entity.getCorporateRepresentative() + "', 1, 1, NULL, " + logo + ", " + license + ", '" + ststus2 + "', '" + entity.getAuditRemark() + "',"+description+", NULL, NULL, '" + entity.getWebsite() + "', NULL, "+industry+", NULL, "+scale+", '" + entity.getAddress() + "',  '" + disableTime + "', '" + entity.getContactPhone() + "', '" + entity.getContactName() + "', '" + entity.getContactEmail() + "', -1,'" + entity.getContactPosition() + "');";
                }
                upmsOrgSql.add(upmsorgSql);



                String passId = "166198771" + RandomUtil.randomNumbers(10);
                String upmspass = "INSERT INTO `upms_pass`(`id`, `name`, `tenant_id`, `system_id`, `open_time`, `expire_time`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `status`, `admin_id`) VALUES " +
                        "("+passId+", '"+entity.getCompanyName2()+"', "+tenantId+", 7, '2021-04-01 14:30:05', NULL, "+createTime+", 1, '"+updateTime+"', 1, 0, 0, "+id+");";
                upmsPassSql.add(upmspass);

                String userpassId = "166198772" + RandomUtil.randomNumbers(10);
                String upmsuserpassSql = "INSERT INTO `upms_user_pass`(`id`, `user_id`, `pass_id`, `create_time`, `update_time`, `status`) VALUES " +
                        "("+userpassId+", "+id+", "+passId+", "+createTime+", '"+updateTime+"', NULL);";
                //维护一个 企业Id(育才网) 和 passId 的map
                CompanyPassMap.put(entity.getCompanyId(),passId);

//                String userpassid = "166198773" + RandomUtil.randomNumbers(10);
//                String upmsuserpassSql2 = "INSERT INTO `upms_user_pass`(`id`, `user_id`, `pass_id`, `create_time`, `update_time`, `status`) VALUES " +
//                        "("+userpassid+", "+id+", '6', "+createTime+", '"+updateTime+"', NULL);";

                upmsUserPassSql.add(upmsuserpassSql);
//                upmsUserPassSql.add(upmsuserpassSql2);




            }else if(StrUtil.isNotBlank(entity.getRelationId())){ //企业的非管理员用户
                String userpassId = "166198775" + RandomUtil.randomNumbers(10);
                if(!CompanyPassMap.containsKey(entity.getRelationId())){
//                    throw new Exception(entity.getRelationId()+"未录入");

                    System.out.println("-- 有异常数据！！！");
                    System.out.println("-- "+ entity.getRelationId()+"未录入");
                    exceptionSql.add("-- "+entity.getUserId()+":"+entity.getUsername()+":"+entity.getRelationId());
                    continue;
                }
                String passId = CompanyPassMap.getOrDefault(entity.getRelationId(),"yicang");
                //添加upms_user_pass
                String upmsuserpassSql = "INSERT INTO `upms_user_pass`(`id`, `user_id`, `pass_id`, `create_time`, `update_time`, `status`) VALUES " +
                        "("+userpassId+", "+id+", "+passId+", "+createTime+", '"+updateTime+"', NULL);";

                upmsUserPassSql.add(upmsuserpassSql);

            }








        }

    }




    /**
     * 初始化map 字段对应关系
     * "Username","password" // - 企业端用户以邮箱作为密码/个人端用户自动生成
     * "Username","salt" //加密盐 - 无
     * "Username","locked" //是否锁定：0否 1是 - 对应 Common_User.IsActive
     * "Username","create_by" //创建人ID - 1
     * "Username","update_time" //修改时间 - 当前时间
     * "Username","update_by" //修改人ID - 1
     * "Username","join_org_count" //加入组织的数量 - 删掉这个字段
     * "Username","auth_status" //实名认证状态 - 企业端用户实名认证状态都是已经实名认证，个人端都是未实名认证。
     * "Username","pwd_init_flag" //  - 邮箱作为密码的pwd_init_flag都是0，随机生成的密码pwd_init_flag都是1
     * "Username","lock_desc" //锁定描述 - 无
     * 身份：个人用户都是在校学生，企业用户不管
     * 毕业时间：个人用户都用student的毕业时间
     *
     * @章贺龙
     */
    public void initMap(){

        //02企业状态
//        constantMap.put("null","310");
        constantMap.put("0","310");
        constantMap.put("1","311");
        constantMap.put("2","312");
        constantMap.put("3","313");
        //03公司规模
        constantMap.put("GSGM0101","304");
        constantMap.put("GSGM0102","305");
        constantMap.put("GSGM0103","306");
        constantMap.put("GSGM0104","307");
        constantMap.put("GSGM0105","308");
        //04公司性质
        constantMap.put("GSXZ0113","149");
        constantMap.put("GSXZ0103","150");
        constantMap.put("GSXZ0102","151");
        constantMap.put("GSXZ0104","152");
        constantMap.put("GSXZ0109","153");
        constantMap.put("GSXZ0107","154");
        constantMap.put("GSXZ0101","155");
        constantMap.put("GSXZ0106","156");
        constantMap.put("GSXZ0105","157");
        constantMap.put("GSXZ0114","158");
        constantMap.put("GSXZ0110","159");
        //05性别
        constantMap.put("XB01001","1");
        constantMap.put("XB01002","2");
        //06学历
        constantMap.put("XL0107","70");
        constantMap.put("XL0109","71");
        constantMap.put("XL0109","72");
        constantMap.put("XL0108","73");
        constantMap.put("XL0102","74");
        constantMap.put("XL0103","75");
        constantMap.put("XL0104","76");
        constantMap.put("XL0106","77");
        //08工作性质
        constantMap.put("GZXZ0103","10001");
        constantMap.put("GZXZ0101","10002");
        constantMap.put("GZXZ0102","10003");
        //01 行业
        constantMap.put("HYFL01006","160");
        constantMap.put("HYFL01002","167");
        constantMap.put("HYFL01003","174");
        constantMap.put("HYFL01005","175");
        constantMap.put("HYFL01004","176");
        constantMap.put("HYFL01006","177");
        constantMap.put("HYFL01001","178");
        constantMap.put("HYFL01006","179");
        constantMap.put("HYFL01006","180");
        constantMap.put("HYFL01008","181");
        constantMap.put("HYFL01009","182");
        constantMap.put("HYFL01003","183");
        constantMap.put("HYFL01006","184");
        constantMap.put("HYFL01003","185");
        constantMap.put("HYFL01001","186");
        constantMap.put("HYFL01042","161");
        constantMap.put("HYFL01042","189");
        constantMap.put("HYFL01047","190");
        constantMap.put("HYFL01043","191");
        constantMap.put("HYFL01050","192");
        constantMap.put("HYFL01046","193");
        constantMap.put("HYFL01050","194");
        constantMap.put("HYFL01010","195");
        constantMap.put("HYFL01042","196");
        constantMap.put("HYFL01034","162");
        constantMap.put("HYFL01038","199");
        constantMap.put("HYFL01034","200");
        constantMap.put("HYFL01034","201");
        constantMap.put("HYFL01034","202");
        constantMap.put("HYFL01034","203");
        constantMap.put("HYFL01034","204");
        constantMap.put("HYFL01034","205");
        constantMap.put("HYFL01040","164");
        constantMap.put("HYFL01033","212");
        constantMap.put("HYFL01041","213");
        constantMap.put("HYFL01040","214");
        constantMap.put("HYFL01024","215");
        constantMap.put("HYFL01041","216");
        constantMap.put("HYFL01041","315");
        constantMap.put("HYFL01040","316");
        constantMap.put("HYFL01016","165");
        constantMap.put("HYFL01017","217");
        constantMap.put("HYFL01016","218");
        constantMap.put("HYFL01015","219");
        constantMap.put("HYFL01017","220");
        constantMap.put("HYFL01015","317");
        constantMap.put("HYFL01018","318");
        constantMap.put("HYFL01016","319");
        constantMap.put("HYFL01028","166");
        constantMap.put("HYFL01027","221");
        constantMap.put("HYFL01028","222");
        constantMap.put("HYFL01013","223");
        constantMap.put("HYFL01014","224");
        constantMap.put("HYFL01027","225");
        constantMap.put("HYFL01027","226");
        constantMap.put("HYFL01022","235");
        constantMap.put("HYFL01029","243");
        constantMap.put("HYFL01012","244");
        constantMap.put("HYFL01026","245");
        constantMap.put("HYFL01011","246");
        constantMap.put("HYFL01022","247");
        constantMap.put("HYFL01022","248");
        constantMap.put("HYFL01022","249");
        constantMap.put("HYFL01022","250");
        constantMap.put("HYFL01025","237");
        constantMap.put("HYFL01025","265");
        constantMap.put("HYFL01026","266");
        constantMap.put("HYFL01025","267");
        constantMap.put("HYFL01025","268");
        constantMap.put("HYFL01037","238");
        constantMap.put("HYFL01036","269");
        constantMap.put("HYFL01039","270");
        constantMap.put("HYFL01050","271");
        constantMap.put("HYFL01000","272");
        constantMap.put("HYFL01050","273");
        constantMap.put("HYFL01037","274");
        constantMap.put("HYFL01048","275");
        constantMap.put("HYFL01050","276");
        constantMap.put("HYFL01050","277");
        constantMap.put("HYFL01050","278");
        constantMap.put("HYFL01031","239");
        constantMap.put("HYFL01031","281");
        constantMap.put("HYFL01030","282");
        constantMap.put("HYFL01032","283");
        constantMap.put("HYFL01050","284");
        constantMap.put("HYFL01050","285");
        constantMap.put("HYFL01050","320");
        constantMap.put("HYFL01020","240");
        constantMap.put("HYFL01021","286");
        constantMap.put("HYFL01023","287");
        constantMap.put("HYFL01019","288");
        constantMap.put("HYFL01045","289");
        constantMap.put("HYFL01019","290");
        constantMap.put("HYFL01021","321");
        constantMap.put("HYFL01020","322");
        constantMap.put("HYFL01020","323");
        constantMap.put("HYFL01035","241");
        constantMap.put("HYFL01035","291");
        constantMap.put("HYFL01051","292");
        constantMap.put("HYFL01051","293");
        constantMap.put("HYFL01051","294");
        constantMap.put("HYFL01049","324");
        constantMap.put("HYFL01035","325");
        constantMap.put("HYFL01044","242");
        constantMap.put("HYFL01044","295");
        constantMap.put("HYFL01044","326");
        constantMap.put("HYFL01044","327");
        constantMap.put("HYFL01044","328");
        constantMap.put("HYFL01044","329");
        constantMap.put("HYFL01044","330");



        /**
        userMap.put("Name","name");//name
        userMap.put("UserName","account");//账号
//        userMap.put("Username","password");//密码
//        userMap.put("Username","salt");//加密盐
//        userMap.put("Username","locked");//是否锁定：0否 1是
        userMap.put("MobilePhone","phone");//手机号
        userMap.put("HeadPhoto","avatar");//头像
        userMap.put("Email","email");//邮箱
        userMap.put("Gender","gender");//性别
        userMap.put("Birthday","birthday");//生日
        userMap.put("CreateTime","create_time");//创建时间
//        userMap.put("Username","create_by");//创建人ID
//        userMap.put("Username","update_time");//修改时间
//        userMap.put("Username","update_by");//修改人ID
        userMap.put("IsDeleted","deleted");//0' COMMENT '是否删除：0否 1是
//        userMap.put("Username","pwd_init_flag");//
//        userMap.put("Username","lock_desc");//锁定描述
        userMap.put("LastLoginTime","last_login_time");//最后一次登录时间
//        userMap.put("Username","join_org_count");//加入组织的数量
//        userMap.put("Username","auth_status");//实名认证状态
        userMap.put("BlockTime","disable_time");//拉黑时间
         **/


    }
    public void out(){
        System.setOut(write);//把创建的打印输出流赋给系统。即系统下次想write输出
        //打印
        System.out.println("--  upmsUserInser 语句");
        System.out.println("--  学生开始");
        upmsUserStudentSql.forEach(System.out::println);
        System.out.println("--  学生结束");

        System.out.println("--  学生拓展信息开始");
        upmsInfoSql.forEach(System.out::println);
        System.out.println("--  学生拓展信息结束");



        System.out.println("--  公司开始");
        upmsUserCompanySql.forEach(System.out::println);
        System.out.println("--  公司结束");

//        Set<String> upmsMemberSql = new HashSet<>();
//        Set<String> upmsTenantSql = new HashSet<>();
//        Set<String> upmsOrgSql = new HashSet<>();
//        Set<String> upmsAgencySql = new HashSet<>();
//        Set<String> upmsPassSql = new HashSet<>();
//        Set<String> upmsUserPassSql = new HashSet<>();

        System.out.println("--  成员开始");
        upmsMemberSql.forEach(System.out::println);
        System.out.println("--  成员结束");

        System.out.println("--  租户开始");
        upmsTenantSql.forEach(System.out::println);
        System.out.println("--  租户结束");

        System.out.println("--  组织开始");
        upmsOrgSql.forEach(System.out::println);
        System.out.println("--  组织结束");

        System.out.println("--  机构开始");
        upmsAgencySql.forEach(System.out::println);
        System.out.println("--  机构结束");

        System.out.println("--  通行证开始");
        upmsPassSql.forEach(System.out::println);
        System.out.println("--  通行证结束");

        System.out.println("--  用户通行证开始");
        upmsUserPassSql.forEach(System.out::println);
        System.out.println("--  用户通行证结束");

        System.out.println("--  账号密码");
        accountAndPass.forEach(System.out::println);
        System.out.println("--  账号密码");

        System.out.println("--  异常账号");
        exceptionSql.forEach(System.out::println);
        System.out.println("--  异常账号");




    }

    public static void main(String[] args) throws Exception {
        DataJdbc dataJdbc = new DataJdbc();
        try {
            //初始化
            dataJdbc.init();
            dataJdbc.initMap();
            //执行逻辑
//            dataJdbc.getFileIdByUrl("http://cdndevstore.myjob500.com/myjob-upload/images/20190718/6369902495162143592993937.png&filename=test.png");
//            dataJdbc.getAddress(" 上海浦东南路256号28～32层");

            Integer commonUserAndBizStudentCount = dataJdbc.commonUserAndBizStudentCount();
            Integer commonUserAndBizCompanyCount = dataJdbc.commonUserAndBizCompanyCount();

            int pageSize = 100;
            int studentPageCount = commonUserAndBizStudentCount%pageSize==0?commonUserAndBizStudentCount/pageSize:commonUserAndBizStudentCount/pageSize+1;
            int companyPageCount = commonUserAndBizCompanyCount%pageSize==0?commonUserAndBizCompanyCount/pageSize:commonUserAndBizCompanyCount/pageSize+1;

            for(int i=1;i<studentPageCount;i++){
                dataJdbc.toCommonUserAndBizStudent(String.valueOf(i),String.valueOf(pageSize));
            }
            for(int i=1;i<companyPageCount;i++){
                dataJdbc.toCommonUserAndBizCompany(String.valueOf(i),String.valueOf(pageSize));
            }
            dataJdbc.CommonUserAndBizStudent2upmsUser();
            dataJdbc.CommonUserAndBizCompany2upmsUser();
            dataJdbc.out();



        } catch (ClassNotFoundException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }finally {
            //关闭
            dataJdbc.close();
        }
    }


}
