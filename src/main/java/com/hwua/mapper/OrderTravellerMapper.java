package com.hwua.mapper;

import com.hwua.pojo.OrderTraveller;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (OrderTraveller)表数据库访问层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:05
 */
@Mapper
public interface OrderTravellerMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    List<OrderTraveller> queryById(String orderId);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<OrderTraveller> queryAll();

    /**
     * 新增数据
     *
     * @param orderTraveller 实例对象
     * @return 影响行数
     */
    int insert(OrderTraveller orderTraveller);

    /**
     * 修改数据
     *
     * @param orderTraveller 实例对象
     * @return 影响行数
     */
    int update(OrderTraveller orderTraveller);

    /**
     * 通过主键删除数据
     *
     * @param orderid 主键
     * @return 影响行数
     */
    int deleteById(String orderid);

}