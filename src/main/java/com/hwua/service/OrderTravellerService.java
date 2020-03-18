package com.hwua.service;

import com.hwua.pojo.OrderTraveller;
import java.util.List;

/**
 * (OrderTraveller)表服务接口
 *
 * @author 马涛
 * @since 2020-03-05 16:49:05
 */
public interface OrderTravellerService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    List<OrderTraveller> queryById(String orderId);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<OrderTraveller> queryAll();

    /**
     * 新增数据
     *
     * @param orderTraveller 实例对象
     * @return 实例对象
     */
    OrderTraveller insert(OrderTraveller orderTraveller);
    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(String orderId);

}