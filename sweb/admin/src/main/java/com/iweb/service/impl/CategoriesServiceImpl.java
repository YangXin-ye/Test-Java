package com.iweb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.Categories;
import com.iweb.entity.ListUserPageReq;
import com.iweb.mapper.CategoriesMapper;
import com.iweb.service.CategoriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.vo.IndexCategoryNamesVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2024-06-13
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements CategoriesService {

    @Resource
    private CategoriesMapper categoriesMapper;

    @Override
    public Page<Categories> listUserPage(Integer pageNum, Integer pageSize, ListUserPageReq listUserPageReq) {
        LambdaQueryWrapper<Categories> lambdaQuery = Wrappers.<Categories>lambdaQuery();
        lambdaQuery.like(StrUtil.isNotBlank(listUserPageReq.getUsername()), Categories::getCategoryname, listUserPageReq.getUsername());

        Page<Categories> page = new Page<>(pageNum, pageSize);
        Page<Categories> results = baseMapper.selectPage(page, lambdaQuery);
        return results;
    }

    @Override
    public void addCategories(Categories categories) {
        baseMapper.insert(categories);
    }

    @Override
    public void deleteOrder(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updateOrder(Categories categories) {
        baseMapper.updateById(categories);
    }

    @Override
    public List<Categories> findAll(Integer pId) {
        // select Name from categories where pId = 5
        LambdaQueryWrapper<Categories> wrapper = new LambdaQueryWrapper();
        wrapper.select(Categories::getCategoryname)// 这样只会返回一个字段 name 的内容
                .eq(Categories::getPId, pId);
        List<Categories> list = this.list(wrapper);
        return list;
    }

    @Override
    public IndexCategoryNamesVO categoryNameByPId(Integer pId) {
        IndexCategoryNamesVO namesVO = categoriesMapper.categoryNameByPId(pId);
        return namesVO;
    }

}
