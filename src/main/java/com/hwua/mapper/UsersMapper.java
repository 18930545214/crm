package com.hwua.mapper;

import com.hwua.pojo.Users;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Users)表数据库访问层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Mapper
public interface UsersMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Users queryById(String id);
    /**
     * 通过用户名查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    Users queryByName(String username);
    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Users> queryAll();

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 影响行数
     */
    int insert(Users users);

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 影响行数
     */
    int update(Users users);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}