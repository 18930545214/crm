package com.hwua.pojo;

import java.util.Date;
import lombok.Data;

/**
 * (Syslog)实体类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Data
public class Syslog {
    /**
    * 主键
    */
    private String id;
    /**
    * 访问时间
    */
    private Date visitTime;
    /**
    * 操作者用户
    */
    private String username;
    /**
    * 访问ip
    */
    private String ip;
    /**
    * 访问url
    */
    private String url;
    /**
    * 执行时长
    */
    private Integer executionTime;
    /**
    * 访问方法
    */
    private String method;

}