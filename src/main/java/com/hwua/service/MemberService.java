package com.hwua.service;

import com.hwua.pojo.Member;
import java.util.List;

/**
 * (Member)表服务接口
 *
 * @author 马涛
 * @since 2020-03-05 16:49:05
 */
public interface MemberService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Member queryById(String id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Member> queryAll();

    /**
     * 新增数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    Member insert(Member member);

    /**
     * 修改数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    Member update(Member member);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}