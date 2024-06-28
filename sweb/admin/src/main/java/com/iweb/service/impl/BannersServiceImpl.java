package com.iweb.service.impl;

import com.iweb.entity.Banners;
import com.iweb.mapper.BannersMapper;
import com.iweb.service.BannersService;
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
 * @since 2024-06-14
 */
@Service
public class BannersServiceImpl extends ServiceImpl<BannersMapper, Banners> implements BannersService {

    @Resource
    private BannersMapper bannersMapper;

    @Override
    public List<Banners> findAll() {
        // 查询全部内容
        return baseMapper.findAll();
    }
}
