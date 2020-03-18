package com.hwua.pojo;

import lombok.Data;

/**
 * (Permission)实体类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Data
public class Permission {
    /**
    * 主键
    */
    private String id;
    /**
    * 权限名
    */
    private String permissionName;
    /**
    * 资源路径
    */
    private String url;

}