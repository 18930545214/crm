package com.hwua.controller;

import com.hwua.pojo.Traveller;
import com.hwua.service.TravellerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Traveller)表控制层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@RestController
@RequestMapping("traveller")
public class TravellerController {
    /**
     * 服务对象
     */
    @Autowired
    private TravellerService travellerService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Traveller selectOne(String id) {
        return this.travellerService.queryById(id);
    }

}