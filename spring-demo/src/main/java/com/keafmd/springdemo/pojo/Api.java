package com.keafmd.springdemo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Keafmd
 *
 * @ClassName: Api
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-11 14:22
 */
@Data
public class Api{
    @ExcelProperty("菜单")
    private String menu;
    @ExcelProperty("功能")
    private String function;
    @ExcelProperty("功能点")
    private String functionPoints;
    @ExcelProperty("接口")
    private String url;
    @ExcelProperty("code")
    private String code;

    @ExcelProperty("序号")
    private String serialNumber;
    @ExcelProperty("角色")
    private String role;
    @ExcelProperty("权限")
    private String perm;


}
