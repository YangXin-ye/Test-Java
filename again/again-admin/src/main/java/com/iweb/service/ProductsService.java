package com.iweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.ListProductsPageReq;
import com.iweb.entity.Products;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
public interface ProductsService extends IService<Products> {

    Page<Products> listUserPage(Integer pageNum, Integer pageSize, ListProductsPageReq listProductsPageReq);

    void addProducts(Products products);

    void deleteOrder(Integer id);

    void updateProduct(Products products);
}
