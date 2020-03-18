package com.hwua.mapper;

import com.hwua.pojo.Syslog;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Syslog)表数据库访问层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Mapper
public interface SyslogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Syslog queryById(String id);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Syslog> queryAll();

    /**
     * 新增数据
     *
     * @param syslog 实例对象
     * @return 影响行数
     */
    int insert(Syslog syslog);

    /**
     * 修改数据
     *
     * @param syslog 实例对象
     * @return 影响行数
     */
    int update(Syslog syslog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}