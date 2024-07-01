package com.iweb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.entity.Categories;
import com.iweb.entity.CategoryTempalte;
import com.iweb.entity.ListCategoriesPageReq;
import com.iweb.mapper.CategoryTempalteMapper;
import com.iweb.service.CategoriesService;
import com.iweb.service.CategoryTempalteService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryTempalteServiceImpl extends ServiceImpl<CategoryTempalteMapper, CategoryTempalte> implements CategoryTempalteService {


    @Autowired
    private CategoriesService categoriesService;
    @Override
    public Page<CategoryTempalte> listUserPage(Integer pageNum, Integer pageSize, ListCategoriesPageReq listCategoriesPageReq) {
        LambdaQueryWrapper<CategoryTempalte> lambdaQuery = Wrappers.<CategoryTempalte>lambdaQuery();
        lambdaQuery.like(StrUtil.isNotBlank(listCategoriesPageReq.getName()), CategoryTempalte::getCategoryname, listCategoriesPageReq.getName());

        Page<CategoryTempalte> page = new Page<>(pageNum, pageSize);
        Page<CategoryTempalte> resultPage = baseMapper.selectPage(page, lambdaQuery);
        return resultPage;
    }

    @Override
    public void addProducts(CategoryTempalte categoryTempalte) {
        baseMapper.insert(categoryTempalte);

    }

    @Override
    public void deleteOrder(Integer id) {
        baseMapper.deleteById(id);
        CategoryTempalte categoryTempalte = baseMapper.selectById(id);
        String categoryname = categoryTempalte.getCategoryname();
        QueryWrapper<Categories> categoriesQueryWrapper = new QueryWrapper<>();
        categoriesQueryWrapper.eq("categoryname",categoryname);
        categoriesService.remove(categoriesQueryWrapper);
    }

    @Override
    public void updateProduct(CategoryTempalte categoryTempalte) {
        baseMapper.updateById(categoryTempalte);
    }
}
