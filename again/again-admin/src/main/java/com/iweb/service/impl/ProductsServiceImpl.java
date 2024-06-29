package com.iweb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.entity.ListProductsPageReq;
import com.iweb.entity.Products;
import com.iweb.mapper.ProductsMapper;
import com.iweb.service.ProductsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {

    @Override
    public Page<Products> listUserPage(Integer pageNum, Integer pageSize, ListProductsPageReq listProductsPageReq) {
        LambdaQueryWrapper<Products> lambdaQuery = Wrappers.<Products>lambdaQuery();
        lambdaQuery.like(StrUtil.isNotBlank(listProductsPageReq.getName()), Products::getName, listProductsPageReq.getName());

        Page<Products> page = new Page<>(pageNum, pageSize);
        Page<Products> resultPage = baseMapper.selectPage(page, lambdaQuery);
        return resultPage;
    }

    @Override
    public void addProducts(Products products) {
        baseMapper.insert(products);
    }

    @Override
    public void deleteOrder(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updateProduct(Products products) {
        baseMapper.updateById(products);
    }
}
