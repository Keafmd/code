package com.keafmd.springdemo.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.keafmd.springdemo.pojo.Api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Keafmd
 *
 * @ClassName: BatchImport
 * @Description: 批量导入
 * @author: 牛哄哄的柯南
 * @date: 2022-04-11 14:20
 */
public class BatchImport {

    //读取excel文件
    public static void readExcel() throws FileNotFoundException {
        String fileName = "C:\\Users\\章贺龙\\Desktop\\inFile1.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Api.class, new ExcelListener()).sheet().doRead();
    }

    public static void main(String[] args) throws FileNotFoundException {
        readExcel();
    }




}
