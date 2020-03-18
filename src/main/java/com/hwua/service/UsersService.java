package com.hwua.service;

import com.hwua.pojo.Users;
import com.hwua.pojo.UsersRole;

import java.util.List;

/**
 * (Users)表服务接口
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
public interface UsersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Users queryById(String id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Users> queryAll();
    /**
     * 通过用户名查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    Users queryByName(String username);

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    Users addUser(Users users);
    int addUsersRole(UsersRole usersRole);

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    Users update(Users users);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}