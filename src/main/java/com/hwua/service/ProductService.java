package com.hwua.service;

import com.hwua.pojo.Product;
import java.util.List;

/**
 * (Product)表服务接口
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
public interface ProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product queryById(String id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Product> queryAll();

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product insert(Product product)throws Exception;

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product update(Product product)throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(String id)throws Exception;

}