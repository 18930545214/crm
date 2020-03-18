package com.hwua.mapper;

import com.hwua.pojo.Product;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Product)表数据库访问层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Mapper
public interface ProductMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product queryById(String id);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Product> queryAll();

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 影响行数
     */
    int insert(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 影响行数
     */
    int update(Product product);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}