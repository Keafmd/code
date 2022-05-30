package com.keafmd.springdemo.dataMigration;

import lombok.Data;

/**
 * Keafmd
 *
 * @ClassName: AddressSearchDTO
 * @Description: 参考文档：https://lbs.qq.com/service/webService/webServiceGuide/webServiceSearch
 * @author: 牛哄哄的柯南
 * @date: 2022-03-24 15:35
 */
@Data
public class AddressSearchDTO {

    /**
     * 搜索关键字，长度最大96个字节
     */
    private String keyword;

    /**
     * 范围
     */
    private String boundary;

    /**
     * 开发密钥（Key）
     */
    private String key;



    //以下为非必需的条件
    /**
     * 筛选条件
     */
    private String filter;

    /**
     * 排序
     */
    private String orderby;

    /**
     * 每页条目数，最大限制为20条，默认为10条
     */
    private String pageSize;

    /**
     * 第x页，默认第1页
     */
    private String pageIndex;

    /**
     * 返回格式 支持JSON/JSONP，默认JSON
     */
    private String output;

    /**
     * JSONP方式回调函数
     */
    private String callback;

}
