package com.hwua.service;

import com.hwua.pojo.Orders;
import java.util.List;

/**
 * (Orders)表服务接口
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
public interface OrdersService {

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
    List<Orders> queryByproductId(String productId);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Orders> queryAll();

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders insert(Orders orders);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders update(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}