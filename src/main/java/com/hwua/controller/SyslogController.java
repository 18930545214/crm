package com.hwua.controller;

import com.hwua.pojo.Syslog;
import com.hwua.service.SyslogService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * (Syslog)表控制层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@RestController
@RequestMapping("syslog")
public class SyslogController {
    /**
     * 服务对象
     */
    @Autowired
    private SyslogService syslogService;
    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("queryAll")
    public ModelAndView queryAll() {
        List<Syslog> syslogList = syslogService.queryAll();
        ModelAndView mv = new ModelAndView("syslog-list");
        mv.addObject("syslogList",syslogList);
        return mv;
    }

}