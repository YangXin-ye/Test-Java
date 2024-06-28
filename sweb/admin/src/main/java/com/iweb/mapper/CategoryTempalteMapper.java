package com.iweb.mapper;

import com.iweb.entity.CategoryTempalte;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2024-06-13
 */
@Mapper
public interface CategoryTempalteMapper extends BaseMapper<CategoryTempalte> {

    List<CategoryTempalte> categoryName();
}
