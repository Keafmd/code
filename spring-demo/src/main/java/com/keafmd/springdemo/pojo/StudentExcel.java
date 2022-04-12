package com.keafmd.springdemo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StudentExcel {
    //设置excel表头名称
    @ExcelProperty(value = "学号")
    private Integer sno;
    @ExcelProperty(value = "姓名")
    private String sname;
    @ExcelProperty(value = "性别")
    private Integer ssex;

}