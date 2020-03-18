package com.hwua.mapper;

import com.hwua.pojo.UsersRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (UsersRole)表数据库访问层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Mapper
public interface UsersRoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    List<UsersRole> queryById(String userid);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<UsersRole> queryAll();

    /**
     * 新增数据
     *
     * @param usersRole 实例对象
     * @return 影响行数
     */
    int insert(UsersRole usersRole);

    /**
     * 修改数据
     *
     * @param usersRole 实例对象
     * @return 影响行数
     */
    int update(UsersRole usersRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteByRoleId(String roleId);

}