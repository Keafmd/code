package com.keafmd.springdemo.test;

import com.alibaba.excel.EasyExcel;
import com.keafmd.springdemo.pojo.Api;

public class TestEasyExcel {
 
    public static void main(String[] args) {
        String filename = "C:\\Users\\章贺龙\\Desktop\\inFile.xlsx";
//        testWriteExcel(filename);
        testReadExcel(filename);
    }

    public static void testReadExcel(String filename){
        //实现excel读操作

        EasyExcel.read(filename, Api.class,new ExcelListener()).sheet().doRead();
    }

 
//    public static void testWriteExcel(String filename){
//        //实现excel写的操作
//
//        //调用easyexcel里面的方法实现写操作
//        //write方法两个参数：第一个参数文件路径名称，第二个参数实体类class      getData()是要写的入的实体类List
//        EasyExcel.write(filename,Api.class).sheet("学生列表").doWrite(getData());
//    }
 
 
//    //创建方法返回list集合
//    private static List<Api> getData() {
//        List<Api> list = new ArrayList<>();
//        String[] name={"tom","jack","tony","小明","小红"};
//        for (int i = 0; i < 5; i++) {
//            Api data = new Api();
//            data.setSno(i);
//            data.setSname(name[i]);
//            data.setSsex(1);
//            list.add(data);
//        }
//        return list;
//    }
}