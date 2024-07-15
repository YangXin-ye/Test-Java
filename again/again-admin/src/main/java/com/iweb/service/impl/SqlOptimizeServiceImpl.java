package com.iweb.service.impl;

import com.iweb.entity.DbOrders;
import com.iweb.mapper.SqlOptimizeMapper;
import com.iweb.service.SqlOptimizeService;
import com.iweb.vo.SqlOptimizeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：
 * @Package：com.iweb.service.impl
 * @Project：Test-Java
 * @name：SqlOptimizeServiceImpl
 * @Date：2024/7/15 12:42
 * @Description:
 */
@Service
public class SqlOptimizeServiceImpl  implements SqlOptimizeService {

    @Autowired
    private SqlOptimizeMapper sqlOptimizeMapper;
    @Override
    public List<DbOrders> list() {
        return sqlOptimizeMapper.list();
    }

    @Override
    public List<DbOrders> list2() {
        return sqlOptimizeMapper.list2();
    }

    @Override
    public Integer list3() {
        return sqlOptimizeMapper.list3();
    }

    @Override
    public Integer list4() {
        return sqlOptimizeMapper.list4();
    }

    @Override
    public Integer list5() {
        return sqlOptimizeMapper.list5();
    }

    @Override
    public Integer list6() {
        return sqlOptimizeMapper.list6();
    }

    @Override
    public List<SqlOptimizeVo> list7() {
        return sqlOptimizeMapper.list7();
    }
}
