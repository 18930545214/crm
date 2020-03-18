package com.hwua.service.impl;

import com.hwua.pojo.Traveller;
import com.hwua.mapper.TravellerMapper;
import com.hwua.service.TravellerService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Traveller)表服务实现类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Service("travellerService")
public class TravellerServiceImpl implements TravellerService {
    @Autowired
    private TravellerMapper travellerMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Traveller queryById(String id) {
        return this.travellerMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Traveller> queryAll() {
        return this.travellerMapper.queryAll();
    }

    /**
     * 新增数据
     *
     * @param traveller 实例对象
     * @return 实例对象
     */
    @Override
    public Traveller insert(Traveller traveller) {
        this.travellerMapper.insert(traveller);
        return traveller;
    }

    /**
     * 修改数据
     *
     * @param traveller 实例对象
     * @return 实例对象
     */
    @Override
    public Traveller update(Traveller traveller) {
        this.travellerMapper.update(traveller);
        return this.queryById(traveller.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.travellerMapper.deleteById(id) > 0;
    }
}