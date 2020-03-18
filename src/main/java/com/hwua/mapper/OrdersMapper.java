package com.hwua.mapper;

import com.hwua.pojo.Orders;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * (Orders)表数据库访问层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Mapper
public interface OrdersMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Orders queryById(String id);
    /**
     * 通过ID查询单条数据
     *
     * @param productId 产品id外键
     * @return 实例对象
     */
    List<Orders> queryByproductId(@Param("productId") String productId);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Orders> queryAll();

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int insert(Orders orders);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int update(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}