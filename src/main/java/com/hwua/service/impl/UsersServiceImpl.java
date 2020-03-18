package com.hwua.service.impl;

import com.hwua.mapper.*;
import com.hwua.pojo.*;
import com.hwua.service.UsersService;
import com.hwua.util.MD5Util;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * (Users)表服务实现类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    UsersRoleMapper usersRoleMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    PermissionMapper permissionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Users queryById(String id) {
        Users users = usersMapper.queryById(id);
        List<UsersRole> usersRoles = usersRoleMapper.queryById(id);
        List<Permission> permissionList = new ArrayList<>();
        List<Role> roleList = new ArrayList<>();
        for (UsersRole usersRole:usersRoles){
            Role role = roleMapper.queryById(usersRole.getRoleId());
            List<RolePermission> rolePermissions = rolePermissionMapper.queryById(role.getId());
            for (RolePermission rolePermission:rolePermissions){
                Permission permission = permissionMapper.queryById(rolePermission.getPermissionId());
                permissionList.add(permission);
            }
            role.setPermissions(permissionList);
            roleList.add(role);
        }
        users.setRoles(roleList);
        return users;
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Users> queryAll() {
        return this.usersMapper.queryAll();
    }

    /**
     * 通过用户名查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    @Override
    public Users queryByName(String username) {
        System.out.println("service");
        return usersMapper.queryByName(username);
    }

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    @RequiresPermissions(value = {"user:add"})
    public Users addUser(Users users) {
        String s = MD5Util.md5hash(users.getUsername(), users.getPassword());
        users.setPassword(s);
        this.usersMapper.insert(users);
        return users;
    }

    @Override
    public int addUsersRole(UsersRole usersRole) {
        String[] rid = usersRole.getRoleId().split("#");
        int res = 0;
        for (int i = 1;i<rid.length;i++){
            System.out.println(rid[i]);
            usersRole.setRoleId(rid[i]);
            res = usersRoleMapper.insert(usersRole);
        }
        return res;
    }

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users update(Users users) {
        this.usersMapper.update(users);
        return this.queryById(users.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.usersMapper.deleteById(id) > 0;
    }
}