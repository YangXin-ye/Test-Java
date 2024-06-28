package com.iweb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.ListUserPageReq;
import com.iweb.entity.Orders;
import com.iweb.entity.Users;
import com.iweb.mapper.OrdersMapper;
import com.iweb.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.jmx.export.assembler.MBeanInfoAssembler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2024-06-08
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;


    @Override
    public Orders selectAll() {
        return ordersMapper.selectAll();
    }

    @Override
    public Page<Orders> listUserPage(Integer pageNum, Integer pageSize, ListUserPageReq listUserPageReq) {
        LambdaQueryWrapper<Orders> lambdaQuery = Wrappers.<Orders>lambdaQuery();
        lambdaQuery.like(StrUtil.isNotBlank(listUserPageReq.getUsername()), Orders::getName, listUserPageReq.getUsername());

        Page<Orders> page = new Page<>(pageNum, pageSize);
        Page<Orders> results = baseMapper.selectPage(page, lambdaQuery);
        return results;
    }
    @Override
    public void saveOrder(Orders orders) {
        baseMapper.insert(orders);
    }

    @Override
    public void deleteOrder(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updateOrder(Orders orders) {
        baseMapper.updateById(orders);
    }

}
