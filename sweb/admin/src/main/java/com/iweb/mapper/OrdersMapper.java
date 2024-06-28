package com.iweb.mapper;

import com.iweb.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2024-06-08
 */

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    Orders selectAll();
}
