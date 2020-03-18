package com.hwua.service;

import com.hwua.pojo.Traveller;
import java.util.List;

/**
 * (Traveller)表服务接口
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
public interface TravellerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Traveller queryById(String id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Traveller> queryAll();

    /**
     * 新增数据
     *
     * @param traveller 实例对象
     * @return 实例对象
     */
    Traveller insert(Traveller traveller);

    /**
     * 修改数据
     *
     * @param traveller 实例对象
     * @return 实例对象
     */
    Traveller update(Traveller traveller);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}