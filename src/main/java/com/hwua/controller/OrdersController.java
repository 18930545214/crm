package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.*;
import com.hwua.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Orders)表控制层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:06
 */
@Controller
@RequestMapping("order")
public class OrdersController {
    /**
     * 服务对象
     */
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderTravellerService orderTravellerService;
    @Autowired
    private TravellerService travellerService;
    @Autowired
    private ProductService productService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("queryById/{id}")
    public ModelAndView queryById(@PathVariable("id") String id) {
        Map<String,Object> map = new HashMap<>();
        List<Traveller> list = new ArrayList<>();
        Orders order = ordersService.queryById(id);
        Product product = productService.queryById(order.getProductId());
        System.out.println(product);
        List<OrderTraveller> orderTravellers = orderTravellerService.queryById(id);
        for (OrderTraveller orderTraveller:orderTravellers){
            Traveller traveller = travellerService.queryById(orderTraveller.getTravellerId());
            list.add(traveller);
        }
        Member member = memberService.queryById(order.getMemberId());
        order.setMember(member);
        map.put("order",order);
        map.put("product",product);
        map.put("traveller",list);
        ModelAndView mv = new ModelAndView("orders-show",map);
        return mv;
    }

    /**
     * 查询所有数据数据
     *
     * @return 对象列表
     */
    @GetMapping("/queryAll/{pageNo}/{pageSize}/{id}")
    @ResponseBody
    public PageInfo<Orders> queryAll(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize,@PathVariable("id") String id) {
        List<Orders> orders;
        PageHelper.startPage(pageNo, pageSize);
        if (id.equals("undefined")){
            orders = ordersService.queryAll();
        }else {
            orders = ordersService.queryByproductId(id);
        }
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        return pageInfo;

    }
    @DeleteMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String id) {
        boolean flag = false;
        Map<String,Object> map = new HashMap<>();
        String[] pid = id.split("#");
        for (int i = 1;i<pid.length;i++){
            flag = ordersService.deleteById(pid[i]);
        }
        if (flag){
            map.put("info","success");
        }else {
            map.put("info","failure");
        }
        return map;
    }
}