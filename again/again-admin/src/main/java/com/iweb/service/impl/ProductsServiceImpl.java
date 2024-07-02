package com.iweb.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.entity.Categories;
import com.iweb.entity.CategoryTempalte;
import com.iweb.entity.ListProductsPageReq;
import com.iweb.entity.Products;
import com.iweb.mapper.ProductsMapper;
import com.iweb.service.CategoriesService;
import com.iweb.service.CategoryTempalteService;
import com.iweb.service.ProductsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private CategoriesService categoriesService;

    @Resource
    private CategoryTempalteService categoryTempalteService;

    @Override
    public Page<Products> listProductPage(Integer pageNum, Integer pageSize, ListProductsPageReq listProductsPageReq) {
        LambdaQueryWrapper<Products> lambdaQuery = Wrappers.<Products>lambdaQuery();
        lambdaQuery.like(StrUtil.isNotBlank(listProductsPageReq.getName()), Products::getName, listProductsPageReq.getName());

        Page<Products> page = new Page<>(pageNum, pageSize);
        Page<Products> resultPage = baseMapper.selectPage(page, lambdaQuery);

        List<Products> records = resultPage.getRecords();

        for (Products record : records) {
            List<Categories> list = categoriesService.lambdaQuery().eq(Categories::getPId, record.getId()).list();
            if(CollectionUtils.isNotEmpty(list)){
                List<String> collect = list.stream().map(Categories::getCategoryname).collect(Collectors.toList());
                record.setCategoryName(String.join(", ", collect));
            }
        }
        return resultPage;
    }

    @Override
    public void addProducts(Products products) {
        baseMapper.insert(products);
        // 添加商品对应分类数据
        if (CollUtil.isNotEmpty(products.getCategoryNames())) {
            List<String> categoryNames = products.getCategoryNames();
            List<Categories> insertCategories = new ArrayList<>();
            for (String categoryName : categoryNames) {
                List<Categories> list = categoriesService.lambdaQuery().eq(Categories::getCategoryname, categoryName).list();
                if(CollectionUtil.isEmpty(list)){
                    //新增
                    CategoryTempalte categoryTempalte = new CategoryTempalte();
                    categoryTempalte.setCategoryname(categoryName);
                    categoryTempalteService.save(categoryTempalte);
                    Categories categories = new Categories();
                    categories.setPId(products.getId());
                    categories.setCategoryname(categoryName);
                    insertCategories.add(categories);
                }else{
                    Categories categories = new Categories();
                    categories.setPId(products.getId());
                    categories.setCategoryname(list.get(0).getCategoryname());
                    insertCategories.add(categories);
                }
            }
            categoriesService.saveBatch(insertCategories);
        }
    }

    @Override
    public void deleteOrder(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updateProduct(Products products) {
        baseMapper.updateById(products);
        //删除之前绑定的的分类
        QueryWrapper<Categories> categoriesQueryWrapper = new QueryWrapper<>();
        categoriesQueryWrapper.eq("p_id",products.getId());
        categoriesService.remove(categoriesQueryWrapper);
        // 如果分类名称不为空
        if (CollUtil.isNotEmpty(products.getCategoryNames())) {
            List<String> categoryNames = products.getCategoryNames();
            List<Categories> insertCategories = new ArrayList<>();
            for (String categoryName : categoryNames) {
                List<Categories> list = categoriesService.lambdaQuery().eq(Categories::getCategoryname, categoryName).list();
                if(CollectionUtil.isEmpty(list)){
                    //新增
                    CategoryTempalte categoryTempalte = new CategoryTempalte();
                    categoryTempalte.setCategoryname(categoryName);
                    categoryTempalteService.save(categoryTempalte);
                    Categories categories = new Categories();
                    categories.setPId(products.getId());
                    categories.setCategoryname(categoryName);
                    insertCategories.add(categories);
                }else{
                    Categories categories = new Categories();
                    categories.setPId(products.getId());
                    categories.setCategoryname(list.get(0).getCategoryname());
                    insertCategories.add(categories);
                }
            }
            categoriesService.saveBatch(insertCategories);
        }
    }
}
