package com.hwua.service.impl;

import com.hwua.pojo.Syslog;
import com.hwua.mapper.SyslogMapper;
import com.hwua.service.SyslogService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Syslog)表服务实现类
 *
 * @author 马涛
 * @since 2020-03-05 16:49:07
 */
@Service("syslogService")
public class SyslogServiceImpl implements SyslogService {
    @Autowired
    private SyslogMapper syslogMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Syslog queryById(String id) {
        return this.syslogMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Syslog> queryAll() {
        return this.syslogMapper.queryAll();
    }

    /**
     * 新增数据
     *
     * @param syslog 实例对象
     * @return 实例对象
     */
    @Override
    public Syslog insert(Syslog syslog) {
        System.out.println("添加日志");
        this.syslogMapper.insert(syslog);
        return syslog;
    }

    /**
     * 修改数据
     *
     * @param syslog 实例对象
     * @return 实例对象
     */
    @Override
    public Syslog update(Syslog syslog) {
        this.syslogMapper.update(syslog);
        return this.queryById(syslog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.syslogMapper.deleteById(id) > 0;
    }
}