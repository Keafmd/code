package com.keafmd.springdemo.dataMigration;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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



    Connection connection;
    List<CommonUserAndBizStudent> userList = new ArrayList<>();
    List<CommonUserAndBizCompany> companyList = new ArrayList<>();
    // 预才网 upms
    Map<String,String> userMap = new HashMap<>();
    Map<String,String> orgMap = new HashMap<>();
    Map<String,String> constantMap = new HashMap<>();


    Set<String> upmsUserStudentSql = new HashSet<>();
    Set<String> upmsUserCompanySql = new HashSet<>();
    Set<String> upmsMemberSql = new HashSet<>();
    Set<String> upmsTenantSql = new HashSet<>();
    Set<String> upmsOrgSql = new HashSet<>();
    Set<String> upmsAgencySql = new HashSet<>();
    Set<String> upmsPassSql = new HashSet<>();
    Set<String> upmsUserPassSql = new HashSet<>();

    public DataJdbc() throws FileNotFoundException {
    }

    public void init() throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dburl = "jdbc:sqlserver://10.82.26.150:1433;DatabaseName=master";
        String user = "sa";
        String password = "Password@";
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
        }
        return result;
    }


    /**
     * 获取CommonUserAndBizStudent
     */
    public void toCommonUserAndBizStudent(){
        try {
            ResultSet resultSet = executeQuery("select top 10 * from ciicsqldev.dbo.Common_User a LEFT JOIN ciicsqldev.dbo.Biz_Student b on a.UserId = b.UserId WHERE a.UserType = 1");
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
    public void toCommonUserAndBizCompany(){
        try {
            ResultSet resultSet = executeQuery("select top 10 * from ciicsqldev.dbo.Common_User a LEFT JOIN ciicsqldev.dbo.Biz_Company b on a.UserId = b.UserId WHERE a.UserType = 2");
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
        String password = passwordEncoder.encode(RandomUtil.randomString(16));
        String sql;
        String id;
        String createTime = null;
        String updateTime = DateUtil.formatDateTime(new Date());
        String birthday = null;
        String pwdInitFlag = "1"; //随机生成的密码
        String authStatus = "1"; //学生端为未认证
        for (CommonUserAndBizStudent entity : userList) {
//            if(StrUtil.isNotBlank(entity.getEmail())){
//                password = passwordEncoder.encode(entity.getEmail());
//            }

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
    public void CommonUserAndBizCompany2upmsUser() throws FileNotFoundException {

        //企业用户如有没有邮箱就把密码设置成123456
        String password = passwordEncoder.encode("123456");
        String sql;
        String id;
        String createTime = null;
        String updateTime = DateUtil.formatDateTime(new Date());
        String setUpTime = DateUtil.formatDateTime(new Date());
        String pwdInitFlag = "1";
        String authStatus = "0";//企业端为已认证
        String logo = null;
        String license = null;
        String disableTime = null;
        for (CommonUserAndBizCompany entity : companyList) {

            if(StrUtil.isNotBlank(entity.getEmail())){
                password = passwordEncoder.encode(entity.getEmail());
                pwdInitFlag = "0";
            }
            if(StrUtil.isNotBlank(entity.getCreateTime())){
                createTime = DateUtil.formatDateTime(new Timestamp(Long.parseLong(entity.getCreateTime())));
            }
            if(StrUtil.isNotBlank(entity.getSetupTime())){
                setUpTime = DateUtil.formatDateTime(new Timestamp(Long.parseLong(entity.getSetupTime())));
            }
            id = "166198766" + RandomUtil.randomNumbers(10);
            sql = "INSERT INTO `ciic_iba_upms`.`upms_user`" +
                    "(`id`, `name`, `account`, `password`, `salt`, `locked`, `phone`, `avatar`, `email`, `gender`, `birthday`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `pwd_init_flag`, `lock_desc`, `last_login_time`, `join_org_count`, `auth_status`, `disable_time`) VALUES " +
                    "("+id+", '"+entity.getUsername()+"', '"+entity.getUsername()+"', '"+password+"', NULL, 0, '"+entity.getMobilePhone()+"', NULL, '"+entity.getEmail()+"', NULL, NULL, '"+createTime+"', '1', '"+updateTime+"', '1', 0, "+pwdInitFlag+", NULL, NULL, NULL, "+authStatus+", NULL);";
            upmsUserCompanySql.add(sql);


            //添加一个企业用户 关联了企业
            if(StrUtil.isNotBlank(entity.getCompanyId())) {




                String tenantId =  "166198767" + RandomUtil.randomNumbers(10);
                String upmstenantSql = "INSERT INTO `upms_tenant`(`id`, `code`, `name`, `domain_prefix`, `domain`, `type`, `open_time`, `expire_time`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`) VALUES " +
                        "(" + tenantId + ", '"+entity.getCompanyNo()+"', '"+entity.getCompanyName2()+"', '1', '1', 1, '2021-04-01 14:30:51', '2041-04-01 14:30:54', '"+createTime+"', '1', '"+updateTime+"', '1', 0);";
                upmsTenantSql.add(upmstenantSql);

                String agencyId = "166198768" + RandomUtil.randomNumbers(10);
                String upmsagencySql = "INSERT INTO `upms_agency`(`id`, `create_time`, `update_time`, `name`, `pid`, `sort`, `tree_id`, `tenant_id`, `create_by`, `update_by`, `code` )VALUES " +
                        "("+agencyId+", '"+createTime+"', '"+updateTime+"', '"+entity.getCompanyName()+"', -1, NULL, '1000', "+tenantId+", '1', '1', NULL);";
                upmsAgencySql.add(upmsagencySql);


                String memberId =  "166198769" + RandomUtil.randomNumbers(10);
                String upmsmemberSql = "INSERT INTO `upms_member`(`id`, `tenant_id`, `user_id`, `status`, `locked`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `agency_id`, `name`, `gender`, `phone`, `email`, `another_name`, `birthday`, `code`, `invitation_id`, `post`, `other`, `channel`)VALUES " +
                        "("+memberId+", "+tenantId+", "+id+", NULL, 0, '"+createTime+"', '1', '"+updateTime+"', '1', 0, "+agencyId+", '"+entity.getUsername()+"', NULL, '"+entity.getMobilePhone()+"', '"+entity.getEmail()+"', NULL, NULL, NULL, NULL, NULL, NULL, NULL);";
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
                System.out.println("-- "+disableTime);

                // type ：'"+entity.getCompanyType()+"'
                //'"+entity.getIndustry()+"' 111

                String orgId = "166198770" + RandomUtil.randomNumbers(10);
                String upmsorgSql = "";
                if(disableTime==null){
                    upmsorgSql = "INSERT INTO `upms_org`(`id`, `parent_id`, `group_id`, `create_time`, `update_time`, `credit_code`, `name`, `abbreviation`, `type`, `registered_address`, `office_address`, `establishment_date`, `area`, `area_id`, `street`, `doorplate`, `lat`, `lng`, `representative`, `create_by`, `update_by`, `domain`, `logo`, `licenses`, `status`, `status_desc`, `description`, `photos`, `videos`, `official_website`, `resource`, `industry`, `progress_phase`, `scale`, `address`, `disable_time`, `contact_phone`, `contact_name`, `contact_email`, `sort_num`,`contact_post`) VALUES " +
                            "(" + orgId + ", NULL, NULL, '" + createTime + "', '" + updateTime + "', '" + entity.getOrgCode() + "', '" + entity.getCompanyName2() + "', '" + entity.getCompanyShortName() + "', NULL, '', '', '" + setUpTime + "', NULL, NULL, NULL, NULL, NULL, NULL, '" + entity.getCorporateRepresentative() + "', 1, 1, NULL, " + logo + ", " + license + ", '" + entity.getAuditStatus() + "', '" + entity.getAuditRemark() + "',NULL, NULL, NULL, '" + entity.getWebsite() + "', NULL, 111, NULL, NULL, '" + entity.getAddress() + "', NULL , '" + entity.getContactPhone() + "', '" + entity.getContactName() + "', '" + entity.getContactEmail() + "', -1,'" + entity.getContactPosition() + "');";

                }else {
                    upmsorgSql = "INSERT INTO `upms_org`(`id`, `parent_id`, `group_id`, `create_time`, `update_time`, `credit_code`, `name`, `abbreviation`, `type`, `registered_address`, `office_address`, `establishment_date`, `area`, `area_id`, `street`, `doorplate`, `lat`, `lng`, `representative`, `create_by`, `update_by`, `domain`, `logo`, `licenses`, `status`, `status_desc`, `description`, `photos`, `videos`, `official_website`, `resource`, `industry`, `progress_phase`, `scale`, `address`, `disable_time`, `contact_phone`, `contact_name`, `contact_email`, `sort_num`,`contact_post`) VALUES " +
                            "(" + orgId + ", NULL, NULL, '" + createTime + "', '" + updateTime + "', '" + entity.getOrgCode() + "', '" + entity.getCompanyName2() + "', '" + entity.getCompanyShortName() + "', NULL, '', '', '" + setUpTime + "', NULL, NULL, NULL, NULL, NULL, NULL, '" + entity.getCorporateRepresentative() + "', 1, 1, NULL, " + logo + ", " + license + ", '" + entity.getAuditStatus() + "', '" + entity.getAuditRemark() + "',NULL, NULL, NULL, '" + entity.getWebsite() + "', NULL, 111, NULL, NULL, '" + entity.getAddress() + "', '" + disableTime + "', '" + entity.getContactPhone() + "', '" + entity.getContactName() + "', '" + entity.getContactEmail() + "', -1,'" + entity.getContactPosition() + "');";
                }
                upmsOrgSql.add(upmsorgSql);



                String passId = "166198771" + RandomUtil.randomNumbers(10);
                String upmspass = "INSERT INTO `upms_pass`(`id`, `name`, `tenant_id`, `system_id`, `open_time`, `expire_time`, `create_time`, `create_by`, `update_time`, `update_by`, `deleted`, `status`, `admin_id`) VALUES " +
                        "("+passId+", '"+entity.getCompanyName2()+"', "+tenantId+", 9, '2021-04-01 14:30:05', NULL, '"+createTime+"', 1, '"+updateTime+"', 1, 0, 0, NULL);";
                upmsPassSql.add(upmspass);

                String userpassId = "166198772" + RandomUtil.randomNumbers(10);
                String upmsuserpassSql = "INSERT INTO `upms_user_pass`(`id`, `user_id`, `pass_id`, `create_time`, `update_time`, `status`) VALUES " +
                        "("+userpassId+", "+id+", "+passId+", '"+createTime+"', '"+updateTime+"', NULL);";

                String userpassid = "166198773" + RandomUtil.randomNumbers(10);
                String upmsuserpassSql2 = "INSERT INTO `upms_user_pass`(`id`, `user_id`, `pass_id`, `create_time`, `update_time`, `status`) VALUES " +
                        "("+userpassid+", "+id+", '6', '"+createTime+"', '"+updateTime+"', NULL);";

                upmsUserPassSql.add(upmsuserpassSql);
                upmsUserPassSql.add(upmsuserpassSql2);




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
        constantMap.put("XB01001","1");
        constantMap.put("XB01002","2");


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


    }
    public void out(){
        System.setOut(write);//把创建的打印输出流赋给系统。即系统下次想write输出
        //打印
        System.out.println("--  upmsUserInser 语句");
        System.out.println("--  学生开始");
        upmsUserStudentSql.forEach(System.out::println);
        System.out.println("--  学生结束");

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


    }

    public static void main(String[] args) throws FileNotFoundException {
        DataJdbc dataJdbc = new DataJdbc();
        try {
            //初始化
            dataJdbc.init();
            dataJdbc.initMap();
            //执行逻辑
//            dataJdbc.getFileIdByUrl("http://cdndevstore.myjob500.com/myjob-upload/images/20190718/6369902495162143592993937.png&filename=test.png");
            dataJdbc.toCommonUserAndBizStudent();
            dataJdbc.toCommonUserAndBizCompany();
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
