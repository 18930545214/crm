package com.hwua.pojo;

import lombok.Data;

import java.util.List;

/**
 * (Role)实体类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Data
public class Role {
    /**
    * 主键
    */
    private String id;
    /**
    * 角色名
    */
    private String roleName;
    /**
    * 角色描述
    */
    private String roleDesc;
    private List<Permission> permissions;

}