package com.hwua.service.impl;

import com.hwua.pojo.Product;
import com.hwua.mapper.ProductMapper;
import com.hwua.service.LuceneProService;
import com.hwua.service.ProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Product)表服务实现类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private LuceneProService luceneProService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Product queryById(String id) {
        return this.productMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Product> queryAll() {
        return this.productMapper.queryAll();
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    @RequiresPermissions(value = {"product:add"})
    public Product insert(Product product) throws Exception{
        this.productMapper.insert(product);
        luceneProService.addDocument(product);
        return product;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    @RequiresPermissions(value = {"product:update"})
    public Product update(Product product) throws Exception{
        System.out.println(product);
        String[] pid = product.getId().split("#");
        for (int i = 1;i<pid.length;i++){
            product.setId(pid[i]);
            productMapper.update(product);
            Product product1 = productMapper.queryById(pid[i]);
            luceneProService.updateDocument(product1);
        }
        return product;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @RequiresPermissions(value = {"product:delete"})
    public int deleteById(String id) throws Exception{
        int res = 0;
        String[] pid = id.split("#");
        for (int i = 1;i<pid.length;i++){
            res = productMapper.deleteById(pid[i]);
            luceneProService.deleteDocument(pid[i]);
        }
        return res;
    }
}