package com.iweb.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.*;
import com.iweb.mapper.ProductsMapper;
import com.iweb.service.CategoriesService;
import com.iweb.service.ProductsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.vo.IndexCategoryNamesVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yxy
 * @since 2024-06-13
 */
@Service
@RequiredArgsConstructor
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {
    private final CategoriesService categoriesService;

    @Resource
    private ProductsMapper productsMapper;

    @Override
    public Page<IndexCategoryNamesVO> listProductsPage(Integer pageNum, Integer pageSize, ListProductsPageReq listProductsPageReq) {
        Page<IndexCategoryNamesVO> page = new Page<>(pageNum, pageSize);
        baseMapper.listProductsPage(page, listProductsPageReq);
        return page;
    }

    @Override
    public void addProducts(AddProductsDTO addProductsDTO) {
        baseMapper.insert(addProductsDTO);

        // Set<String> categoryNames = new HashSet<>();
        // if (StrUtil.isNotBlank(addProductsDTO.getCategoryName())) {
        //     categoryNames.add(addProductsDTO.getCategoryName());
        // }
        // if (StrUtil.isNotBlank(addProductsDTO.getNewCategoryName())) {
        //     categoryNames.add(addProductsDTO.getNewCategoryName());
        // }
        // if (CollUtil.isNotEmpty(categoryNames)) {
        //     List<Categories> insertCategories = categoryNames.stream()
        //             .map(categoryName -> {
        //                 Categories categories = new Categories();
        //                 categories.setPId(addProductsDTO.getId());
        //                 categories.setCategoryname(categoryName);
        //                 return categories;
        //             }).collect(Collectors.toList());
        //     categoriesService.saveBatch(insertCategories);
        // }
    }

    @Override
    public void deleteOrder(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updateOrder(Products products) {
        baseMapper.updateById(products);
    }


}
