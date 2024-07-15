package com.iweb.mapper;

import com.iweb.entity.DbOrders;
import com.iweb.vo.SqlOptimizeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author：
 * @Package：com.iweb.mapper
 * @Project：Test-Java
 * @name：SqlOptimizeMapepr
 * @Date：2024/7/15 12:46
 * @Description:
 */

@Mapper
public interface SqlOptimizeMapper {
    List<DbOrders> list();

    List<DbOrders> list2();

    Integer list3();

    Integer list4();

    Integer list5();

    Integer list6();

    List<SqlOptimizeVo> list7();
}
