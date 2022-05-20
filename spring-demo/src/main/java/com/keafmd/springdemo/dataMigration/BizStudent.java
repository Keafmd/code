package com.keafmd.springdemo.dataMigration;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Keafmd
 *
 * @ClassName: BizStudent
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-05-16 14:54
 */
@Data
@Accessors(chain = true)
public class BizStudent {
    private String StudentId;
    private String UserId;
    private String Name;
    private String Gender;
    private String IDType;
    private String IDNo;
    private String Birthday;
    private String WorkYear;
    private String PermanentResidence;
    private String CurrentCity;
    private String GraduateSchool;
    private String Mobile;
    private String Email;
    private String Phone;
    private String WeiXinNo;
    private String QQ;
    private String Education;
    private String Nation;
    private String PostalCode;
    private String IsOverseasWork;
    private String PoliticalAffiliation;
    private String Major;
    private String DetailAddress;
    private String HeadPhoto;
    private String ResumeCount;
    private String IsResumeIntegral;
    private String Specialty;
    private String GraduateTime;
    private String JobCategory;
    private String ModifyTime;
    private String IsVideoIntegral;
    private String NameEN;
    private String DetailAddressEN;
    private String NationEN;
    private String AutoRecommend;
    private String LastHintTime;
    private String Covid19;
    private String PushPositionTime;
    private String StudentOrigin;
}
