package com.keafmd.springdemo.dataMigration;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Keafmd
 *
 * @ClassName: CommonUser
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-05-16 13:41
 */
@Data
@Accessors(chain = true)
public class CommonUser {
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

}
