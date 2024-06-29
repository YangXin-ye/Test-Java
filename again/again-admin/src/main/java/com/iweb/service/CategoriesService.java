package com.iweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iweb.entity.Categories;
import com.iweb.entity.ListCategoriesPageReq;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
public interface CategoriesService extends IService<Categories> {

    Page<Categories> listCategoryPage(Integer pageNum, Integer pageSize, ListCategoriesPageReq listCategoriesPageReq);

    void addCategory(Categories categories);

    void deleteCategory(Integer id);

    void updateCategory(Categories categories);
}
