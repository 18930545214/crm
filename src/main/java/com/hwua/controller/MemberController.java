package com.hwua.controller;

import com.hwua.pojo.Member;
import com.hwua.service.MemberService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Member)表控制层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:05
 */
@RestController
@RequestMapping("member")
public class MemberController {
    /**
     * 服务对象
     */
    @Autowired
    private MemberService memberService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Member selectOne(String id) {
        return this.memberService.queryById(id);
    }

}