package com.iweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iweb.entity.Datas;
import com.iweb.entity.ListDataPageReq;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
public interface DataService extends IService<Datas> {

    Datas selectAll();

    Page<Datas> listDataPage(Integer pageNum, Integer pageSize, ListDataPageReq listDataPageReq);

    void saveData(Datas data);

    void deleteData(Integer id);

    void updateData(Datas data);
}
