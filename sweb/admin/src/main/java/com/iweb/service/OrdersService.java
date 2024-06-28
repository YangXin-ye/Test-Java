package com.iweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.ListUserPageReq;
import com.iweb.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iweb.entity.Users;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-08
 */
public interface OrdersService extends IService<Orders> {


    Orders selectAll();

    Page<Orders> listUserPage(Integer pageNum, Integer pageSize, ListUserPageReq listUserPageReq);

    void saveOrder(Orders orders);

    void deleteOrder(Integer id);

    void updateOrder(Orders orders);
}
