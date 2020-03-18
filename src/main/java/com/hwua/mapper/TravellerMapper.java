package com.hwua.mapper;

import com.hwua.pojo.Traveller;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Traveller)表数据库访问层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Mapper
public interface TravellerMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Traveller queryById(String id);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Traveller> queryAll();

    /**
     * 新增数据
     *
     * @param traveller 实例对象
     * @return 影响行数
     */
    int insert(Traveller traveller);

    /**
     * 修改数据
     *
     * @param traveller 实例对象
     * @return 影响行数
     */
    int update(Traveller traveller);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}