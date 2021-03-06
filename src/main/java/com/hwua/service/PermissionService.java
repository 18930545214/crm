package com.hwua.service;

import com.hwua.pojo.Permission;
import java.util.List;

/**
 * (Permission)表服务接口
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(String id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Permission> queryAll();

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}