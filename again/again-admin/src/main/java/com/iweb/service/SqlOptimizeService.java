package com.iweb.service;

import com.iweb.entity.DbOrders;
import com.iweb.vo.SqlOptimizeVo;

import java.util.List;

/**
 * @Author：
 * @Package：com.iweb.service
 * @Project：Test-Java
 * @name：SqlOptimizeService
 * @Date：2024/7/15 12:42
 * @Description:
 */
public interface SqlOptimizeService {


    List<DbOrders> list();

    List<DbOrders> list2();

    Integer list3();

    Integer list4();

    Integer list5();

    Integer list6();

    List<SqlOptimizeVo> list7();
}
