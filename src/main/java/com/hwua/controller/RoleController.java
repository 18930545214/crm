package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.pojo.RolePermission;
import com.hwua.pojo.Users;
import com.hwua.service.PermissionService;
import com.hwua.service.RoleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Role)表控制层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    Map<String,Object> map = new HashMap<>();
    /**
     * 查询所有数据数据
     *
     * @return 对象列表
     */
    @GetMapping("/queryAll")
    public PageInfo<Role> queryAll(Integer pageNo, Integer pageSize) throws Exception{
        List<Role> roles;
        if (pageNo==null){
            roles = roleService.queryAll();
        }else {
            PageHelper.startPage(pageNo, pageSize);
            roles = roleService.queryAll();
        }
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        System.out.println(pageInfo);
        return pageInfo;
    }
    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @PostMapping("/addRole")
    public Map<String,Object> addRole(Role role) throws Exception{
        map.clear();
        role = roleService.insert(role);
        if (role!=null){
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
        boolean flag = roleService.deleteById(id);
        if (flag){
            map.put("info","success");
        }else {
            map.put("info","failure");
        }
        return map;
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("show/{id}")
    public ModelAndView selectOne(@PathVariable("id") String id) {
        Role role = roleService.queryById(id);
        ModelAndView mv = new ModelAndView("role-show");
        mv.addObject("role",role);
        return mv;

    }

    /**
     * 新增数据
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    @PostMapping("/addPermission")
    @ResponseBody
    public Map<String,Object> addPermission(RolePermission rolePermission) throws Exception{
        int i = roleService.addPermission(rolePermission);
        if (i>0){
            map.put("info","success");
        }else {
            map.put("info","failure");
        }
        return map;
    }
}