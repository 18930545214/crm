package com.hwua.pojo;

import lombok.Data;

/**
 * (Traveller)实体类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Data
public class Traveller {
    /**
    * 主键
    */
    private String id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 性别
    */
    private String sex;
    /**
    * 电话号码
    */
    private String phoneNum;
    /**
    * 证件类型 0身份证 1护照 2军官证
    */
    private Integer credentialsType;
    /**
    * 证件号码
    */
    private String credentialsNum;
    /**
    * 旅客类型0成人 1儿童
    */
    private Integer travellerType;

}