package com.hwua.service.impl;

import com.hwua.mapper.PermissionMapper;
import com.hwua.mapper.RolePermissionMapper;
import com.hwua.mapper.UsersRoleMapper;
import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.mapper.RoleMapper;
import com.hwua.pojo.RolePermission;
import com.hwua.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private UsersRoleMapper usersRoleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override

    public Role queryById(String id) {
        Role role = roleMapper.queryById(id);
        List<Permission> list = new ArrayList<>();
        List<RolePermission> rolePermissions = rolePermissionMapper.queryById(id);
        for (RolePermission rolePermission:rolePermissions){
            Permission permission = permissionMapper.queryById(rolePermission.getPermissionId());
            list.add(permission);
        }
        role.setPermissions(list);
        return role;
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override

    public List<Role> queryAll() {
        return this.roleMapper.queryAll();
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象add
     */
    @Override
    @RequiresPermissions(value = {"role:add"})
    public Role insert(Role role) {
        this.roleMapper.insert(role);
        return role;
    }
    @Override
    @RequiresPermissions(value = {"role:add"})
    public int addPermission(RolePermission rolePermission) {
        int res = 0;
        String[] permissionId = rolePermission.getPermissionId().split("#");
        for (int i = 1;i<permissionId.length;i++){
            System.out.println(permissionId[i]);
            rolePermission.setPermissionId(permissionId[i]);
            res = rolePermissionMapper.insert(rolePermission);
        }
        return res;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleMapper.update(role);
        return this.queryById(role.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @Transactional
    @RequiresPermissions(value = {"role:delete"})
    public boolean deleteById(String id) {
        boolean flag = false;
        flag = rolePermissionMapper.deleteByPermissionId(id)> 0;
        flag = roleMapper.deleteById(id) > 0;
        flag = usersRoleMapper.deleteByRoleId(id)>0;
        return flag;
    }
}