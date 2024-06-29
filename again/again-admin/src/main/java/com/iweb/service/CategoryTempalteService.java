package com.iweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.CategoryTempalte;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iweb.entity.ListCategoriesPageReq;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
public interface CategoryTempalteService extends IService<CategoryTempalte> {

    Page<CategoryTempalte> listUserPage(Integer pageNum, Integer pageSize, ListCategoriesPageReq listCategoriesPageReq);

    void addProducts(CategoryTempalte categoryTempalte);

    void deleteOrder(Integer id);

    void updateProduct(CategoryTempalte categoryTempalte);
}
