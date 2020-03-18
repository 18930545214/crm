package com.hwua.service.impl;

import com.hwua.pojo.Member;
import com.hwua.mapper.MemberMapper;
import com.hwua.service.MemberService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Member)表服务实现类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:05
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Member queryById(String id) {
        return this.memberMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Member> queryAll() {
        return this.memberMapper.queryAll();
    }

    /**
     * 新增数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    @Override
    public Member insert(Member member) {
        this.memberMapper.insert(member);
        return member;
    }

    /**
     * 修改数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    @Override
    public Member update(Member member) {
        this.memberMapper.update(member);
        return this.queryById(member.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.memberMapper.deleteById(id) > 0;
    }
}