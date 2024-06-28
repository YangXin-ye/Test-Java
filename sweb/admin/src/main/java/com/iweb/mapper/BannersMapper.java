package com.iweb.mapper;

import com.iweb.entity.Banners;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2024-06-14
 */

@Mapper
public interface BannersMapper extends BaseMapper<Banners> {

    List<Banners> findAll();
}
