package com.hwua.mapper;

import com.hwua.pojo.RolePermission;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (RolePermission)表数据库访问层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Mapper
public interface RolePermissionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    List<RolePermission> queryById(String roleId);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<RolePermission> queryAll();

    /**
     * 新增数据
     *
     * @param rolePermission 实例对象
     * @return 影响行数
     */
    int insert(RolePermission rolePermission);

    /**
     * 修改数据
     *
     * @param rolePermission 实例对象
     * @return 影响行数
     */
    int update(RolePermission rolePermission);

    /**
     * 通过主键删除数据
     *
     * @param permissionId 主键
     * @return 影响行数
     */
    int deleteByPermissionId(String permissionId);

}