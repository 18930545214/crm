package com.hwua.pojo;

import lombok.Data;

/**
 * (Member)实体类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:05
 */
@Data
public class Member {
    
    private String id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 昵称
    */
    private String nickname;
    /**
    * 电话号码
    */
    private String phoneNum;
    /**
    * 邮箱
    */
    private String email;

}