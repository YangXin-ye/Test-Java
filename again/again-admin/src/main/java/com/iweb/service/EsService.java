package com.iweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.District;

import java.util.List;

/**
 * @Author：
 * @Package：com.iweb.service
 * @Project：Test-Java
 * @name：EsService
 * @Date：2024/7/2 17:16
 * @Description:
 */
public interface EsService {
    void syncData();

    Page<District> list(String name, Integer pageNum, Integer pageSize);

}
