package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.service.PermissionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Permission)表控制层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Autowired
    private PermissionService permissionService;
    Map<String,Object> map = new HashMap<>();

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Permission selectOne(String id) {
        return null;
    }
    /**
     * 查询所有数据数据
     *
     * @return 对象列表
     */
    @GetMapping("/queryAll")
    public PageInfo<Permission> queryAll(Integer pageNo, Integer pageSize) throws Exception{
        List<Permission> permissions;
        if (pageNo == null){
            permissions = permissionService.queryAll();
        }else {
            PageHelper.startPage(pageNo, pageSize);
            permissions = permissionService.queryAll();
        }
        PageInfo<Permission> pageInfo = new PageInfo<>(permissions);
        System.out.println(pageInfo);
        return pageInfo;
    }
    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @PostMapping("/add")
    public Map<String,Object> insert(Permission permission) throws Exception{
        map.clear();
        permission = permissionService.insert(permission);
        if (permission!=null){
            map.put("info","success");
        }else {
            map.put("info","failure");
        }
        return map;
    }
    /**
     * 新增数据
     *
     * @param id 主键
     * @return 成功数据
     */
    @DeleteMapping("/delete")
    public Map<String,Object> delete(String id) throws Exception{
        map.clear();
        boolean flag = permissionService.deleteById(id);
        if (flag){
            map.put("info","success");
        }else {
            map.put("info","failure");
        }
        return map;
    }
}