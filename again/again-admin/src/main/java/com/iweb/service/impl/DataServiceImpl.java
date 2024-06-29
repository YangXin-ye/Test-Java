package com.iweb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.entity.Datas;
import com.iweb.entity.ListDataPageReq;
import com.iweb.mapper.DataMapper;
import com.iweb.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Datas> implements DataService {

    @Resource
    private DataMapper dataMapper;

    @Override
    public Datas selectAll() {
        return dataMapper.selectAll();
    }

    @Override
    public Page<Datas> listDataPage(Integer pageNum, Integer pageSize, ListDataPageReq listDataPageReq) {
        LambdaQueryWrapper<Datas> lambdaQuery = Wrappers.<Datas>lambdaQuery();
        lambdaQuery.like(StrUtil.isNotBlank(listDataPageReq.getName()), Datas::getName, listDataPageReq.getName());

        Page<Datas> page = new Page<>(pageNum, pageSize);
        Page<Datas> results = baseMapper.selectPage(page, lambdaQuery);
        return results;
    }
    @Override
    public void saveData(Datas data) {
        baseMapper.insert(data);
    }

    @Override
    public void deleteData(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updateData(Datas data) {
        baseMapper.updateById(data);
    }

}
