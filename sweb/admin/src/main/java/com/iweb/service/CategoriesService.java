package com.iweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.Categories;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iweb.entity.ListUserPageReq;
import com.iweb.vo.IndexCategoryNamesVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-13
 */
public interface CategoriesService extends IService<Categories> {

    Page<Categories> listUserPage(Integer pageNum, Integer pageSize, ListUserPageReq listUserPageReq);

    void addCategories(Categories categories);

    void deleteOrder(Integer id);

    void updateOrder(Categories categories);

    List<Categories> findAll(Integer pId);

    IndexCategoryNamesVO categoryNameByPId(Integer pId);

}
