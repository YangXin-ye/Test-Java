package com.iweb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.CategoryTempalte;
import com.iweb.entity.ListUserPageReq;
import com.iweb.entity.Orders;
import com.iweb.mapper.CategoryTempalteMapper;
import com.iweb.mapper.OrdersMapper;
import com.iweb.service.CategoryTempalteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CategoryTempalteServiceImpl extends ServiceImpl<CategoryTempalteMapper, CategoryTempalte> implements CategoryTempalteService {

    @Resource
    private CategoryTempalteMapper categoryTempalteMapper;


    @Override
    public Page<CategoryTempalte> listUserPage(Integer pageNum, Integer pageSize, ListUserPageReq listUserPageReq) {
        LambdaQueryWrapper<CategoryTempalte> lambdaQuery = Wrappers.<CategoryTempalte>lambdaQuery();
        lambdaQuery.like(StrUtil.isNotBlank(listUserPageReq.getUsername()), CategoryTempalte::getCategoryname, listUserPageReq.getUsername());

        Page<CategoryTempalte> page = new Page<>(pageNum, pageSize);
        Page<CategoryTempalte> results = baseMapper.selectPage(page, lambdaQuery);
        return results;
    }
    @Override
    public void saveOrder(CategoryTempalte categoryTempalte) {
        baseMapper.insert(categoryTempalte);
    }

    @Override
    public void deleteOrder(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updateOrder(CategoryTempalte categoryTempalte) {
        baseMapper.updateById(categoryTempalte);
    }

    @Override
    public List<CategoryTempalte> categoryName() {
        return categoryTempalteMapper.categoryName();
    }

}
