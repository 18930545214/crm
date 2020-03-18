package com.hwua.service;

import com.hwua.pojo.Role;
import com.hwua.pojo.RolePermission;

import java.util.List;

/**
 * (Role)表服务接口
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(String id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Role> queryAll();

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);
    int addPermission(RolePermission rolePermission);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}