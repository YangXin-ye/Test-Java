package com.iweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.AddProductsDTO;
import com.iweb.entity.ListProductsPageReq;
import com.iweb.entity.ListUserPageReq;
import com.iweb.entity.Products;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iweb.vo.IndexCategoryNamesVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-13
 */
public interface ProductsService extends IService<Products> {

    Page<IndexCategoryNamesVO> listProductsPage(Integer pageNum, Integer pageSize, ListProductsPageReq listProductsPageReq);

    void addProducts(AddProductsDTO addProductsDTO);

    void deleteOrder(Integer id);

    void updateOrder(Products products);
}
