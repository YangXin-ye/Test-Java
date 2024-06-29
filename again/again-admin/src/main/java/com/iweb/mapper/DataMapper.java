package com.iweb.mapper;

import com.iweb.entity.Datas;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
public interface DataMapper extends BaseMapper<Datas> {

    Datas selectAll();
}
