package com.keafmd.springdemo.dataMigration;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Keafmd
 *
 * @ClassName: Address
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-03-24 17:55
 */
@Data
@Accessors(chain = true)
public class Address {

    private String id;
    private String title;
    private String address;
    private String tel;
    private String type;
    private String category;

    private Object location;

    private String lat;
    private String lng;

    private String distance;

    private Object adInfo;

    private String adcode;
    private String province;
    private String city;
    private String district;
}
