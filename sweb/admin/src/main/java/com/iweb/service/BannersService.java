package com.iweb.service;

import com.iweb.entity.Banners;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-14
 */
public interface BannersService extends IService<Banners> {

    List<Banners> findAll();
}
