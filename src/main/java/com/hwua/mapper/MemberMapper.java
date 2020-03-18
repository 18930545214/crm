package com.hwua.mapper;

import com.hwua.pojo.Member;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Member)表数据库访问层
 *
 * @author 马涛
 * @since 2020-03-05 16:49:05
 */
@Mapper
public interface MemberMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Member queryById(String id);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Member> queryAll();

    /**
     * 新增数据
     *
     * @param member 实例对象
     * @return 影响行数
     */
    int insert(Member member);

    /**
     * 修改数据
     *
     * @param member 实例对象
     * @return 影响行数
     */
    int update(Member member);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}