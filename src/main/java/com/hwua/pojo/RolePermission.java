package com.hwua.pojo;

import lombok.Data;

import java.util.List;

/**
 * (RolePermission)实体类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Data
public class RolePermission {
    
    private String permissionId;
    
    private String roleId;
    private Role role;
    private List<Permission> permissions;

}