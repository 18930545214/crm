package com.hwua.pojo;

import lombok.Data;

import java.util.List;

/**
 * (Users)实体类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Data
public class Users {
    /**
    * 用户主键
    */
    private String id;
    /**
    * email
    */
    private String email;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 电话
    */
    private String phoneNum;
    /**
    * 状态0未开启1开启
    */
    private Integer status;
    private List<Role> roles;

}