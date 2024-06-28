package com.iweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.CategoryTempalte;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iweb.entity.ListUserPageReq;
import com.iweb.entity.Orders;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-13
 */
public interface CategoryTempalteService extends IService<CategoryTempalte> {

    Page<CategoryTempalte> listUserPage(Integer pageNum, Integer pageSize, ListUserPageReq listUserPageReq);

    void saveOrder(CategoryTempalte categoryTempalte);

    void deleteOrder(Integer id);

    void updateOrder(CategoryTempalte categoryTempalte);

    List<CategoryTempalte> categoryName();
}
