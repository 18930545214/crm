package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Product;
import com.hwua.service.LuceneProService;
import com.hwua.service.ProductService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Product)表控制层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@RestController
@RequestMapping("product")
public class ProductController {
    /**
     * 服务对象
     */
    @Autowired
    private ProductService productService;
    @Autowired
    private LuceneProService luceneProService;
    Map<String,Object> map = new HashMap<>();
    //全局检索
    @GetMapping("/queryByName")
    public List<Product> queryProByTerm(String name) throws Exception {
        return luceneProService.searchProsByTerm("productName", name, 20);
    }
    /**
     * 查询所有数据数据
     *
     * @return 对象列表
     */
    @GetMapping("/queryAll/{pageNo}/{pageSize}")
    public PageInfo<Product> queryAll(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) throws Exception{
        PageHelper.startPage(pageNo, pageSize);
        List<Product> products = productService.queryAll();
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }
    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @PostMapping("/add")
    public Map<String,Object> insert(Product product) throws Exception{
        map.clear();
        product = productService.insert(product);
        luceneProService.addDocument(product);
        if (product!=null){
            map.put("info","success");
        }else {
            map.put("info","failure");
        }
        return map;
    }
    @DeleteMapping("/delete")
    public Map<String,Object> delete(String id) throws Exception{
        map.clear();
        boolean flag = false;
        String[] pid = id.split("#");
        for (int i = 1;i<pid.length;i++){
            flag = productService.deleteById(pid[i]);
            luceneProService.deleteDocument(pid[i]);
        }
        if (flag){
            map.put("info","success");
        }else {
            map.put("info","failure");
        }
        return map;
    }
    @PutMapping("/update")
    public Map<String,Object> update(Product product) throws Exception{
        Product update = null;
        map.clear();
        String[] pid = product.getId().split("#");
        for (int i = 1;i<pid.length;i++){
            product.setId(pid[i]);
            update = productService.update(product);
            luceneProService.updateDocument(update);
        }
        if (update!=null){
            map.put("info","success");
        }else {
            map.put("info","failure");
        }
        return map;
    }
}