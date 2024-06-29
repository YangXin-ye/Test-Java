package com.iweb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.entity.Categories;
import com.iweb.entity.ListCategoriesPageReq;
import com.iweb.mapper.CategoriesMapper;
import com.iweb.service.CategoriesService;
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
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements CategoriesService {

    @Override
    public Page<Categories> listCategoryPage(Integer pageNum, Integer pageSize, ListCategoriesPageReq listCategoriesPageReq) {
        LambdaQueryWrapper<Categories> lambdaQuery = Wrappers.<Categories>lambdaQuery();
        lambdaQuery.like(StrUtil.isNotBlank(listCategoriesPageReq.getName()), Categories::getCategoryname, listCategoriesPageReq.getName());

        Page<Categories> page = new Page<>(pageNum, pageSize);
        Page<Categories> resultPage = baseMapper.selectPage(page, lambdaQuery);
        return resultPage;
    }

    @Override
    public void addCategory(Categories categories) {

    }

    @Override
    public void deleteCategory(Integer id) {

    }

    @Override
    public void updateCategory(Categories categories) {

    }
}
