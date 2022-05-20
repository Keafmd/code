package com.keafmd.springdemo.dataMigration;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Keafmd
 *
 * @ClassName: CommonUserAndBizStudent
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-05-16 18:02
 */
@Data
@Accessors(chain = true)
public class CommonUserAndBizStudent {
    private String UserId;
    private String Username;
    private String UserType;
    private String RelationId;
    private String Email;
    private String MobilePhone;
    private String Password;
    private String PasswordFormatId;
    private String PasswordSalt;
    private String RegisterTime;
    private String TryLoginTimes;
    private String IsActive;
    private String ActiveTime;
    private String IsDeleted;
    private String IsSystemAccount;
    private String SystemName;
    private String RepeatLoginTimes;
    private String LastLoginTime;
    private String LastIpAddress;
    private String LastActivityTime;
    private String CreateTime;
    private String Memo;
    private String ResetPasswordToken;
    private String CompanyName;
    private String PayPwd;
    private String IsSend;
    private String SourceId;
    private String PlatSource;
    private String Attr;
    private String ExtID;
    private String FirstActivation;
    private String Remark;
    private String AccountType;
    private String CreationUserId;
    private String ModificationTime;
    private String ModificationUserId;
    private String BlockTime;
    private String FocusService;
    private String zrid;

    private String StudentId;
    private String UserId2;
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
    private String Email2;
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
