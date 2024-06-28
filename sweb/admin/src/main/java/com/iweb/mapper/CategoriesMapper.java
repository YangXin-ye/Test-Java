package com.iweb.mapper;

import com.iweb.entity.Categories;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iweb.vo.IndexCategoryNamesVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface CategoriesMapper extends BaseMapper<Categories> {


    IndexCategoryNamesVO categoryNameByPId(Integer pId);
}
