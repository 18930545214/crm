package com.hwua.service.impl;

import com.hwua.mapper.RolePermissionMapper;
import com.hwua.pojo.Permission;
import com.hwua.mapper.PermissionMapper;
import com.hwua.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Permission)表服务实现类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override

    public Permission queryById(String id) {
        return this.permissionMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override

    public List<Permission> queryAll() {
        return this.permissionMapper.queryAll();
    }

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    @RequiresPermissions(value = {"permission:add"})
    public Permission insert(Permission permission) {
        this.permissionMapper.insert(permission);
        return permission;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    @RequiresPermissions(value = {"permission:update"})
    public Permission update(Permission permission) {
        this.permissionMapper.update(permission);
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @Transactional
    @RequiresPermissions(value = {"permission:delete"})
    public boolean deleteById(String id) {
        boolean flag = false;
        flag = permissionMapper.deleteById(id) > 0;
        flag = rolePermissionMapper.deleteByPermissionId(id)>0;
        return flag;
    }
}