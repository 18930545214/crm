package com.hwua.service.impl;

import com.hwua.pojo.Orders;
import com.hwua.mapper.OrdersMapper;
import com.hwua.service.OrdersService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Orders)表服务实现类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Orders queryById(String id) {
        return this.ordersMapper.queryById(id);
    }
    /**
     * 通过ID查询单条数据
     *
     * @param productId 产品id外键
     * @return 实例对象
     */
    @Override

    public List<Orders> queryByproductId(String productId) {
        return ordersMapper.queryByproductId(productId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override

    public List<Orders> queryAll() {
        return this.ordersMapper.queryAll();
    }

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    @RequiresPermissions(value = {"order:add"})
    public Orders insert(Orders orders) {
        this.ordersMapper.insert(orders);
        return orders;
    }

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    @RequiresPermissions(value = {"order:update"})
    public Orders update(Orders orders) {
        this.ordersMapper.update(orders);
        return this.queryById(orders.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @RequiresPermissions(value = {"order:delete"})
    public boolean deleteById(String id) {
        return this.ordersMapper.deleteById(id) > 0;
    }
}