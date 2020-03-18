package com.hwua.service.impl;

import com.hwua.pojo.OrderTraveller;
import com.hwua.mapper.OrderTravellerMapper;
import com.hwua.service.OrderTravellerService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (OrderTraveller)表服务实现类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Service("orderTravellerService")
public class OrderTravellerServiceImpl implements OrderTravellerService {
    @Autowired
    private OrderTravellerMapper orderTravellerMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public List<OrderTraveller> queryById(String orderId) {
        return this.orderTravellerMapper.queryById(orderId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<OrderTraveller> queryAll() {
        return this.orderTravellerMapper.queryAll();
    }

    /**
     * 新增数据
     *
     * @param orderTraveller 实例对象
     * @return 实例对象
     */
    @Override
    public OrderTraveller insert(OrderTraveller orderTraveller) {
        this.orderTravellerMapper.insert(orderTraveller);
        return orderTraveller;
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String orderId) {
        return this.orderTravellerMapper.deleteById(orderId) > 0;
    }
}