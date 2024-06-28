package com.iweb.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.ListProductsPageReq;
import com.iweb.entity.ListUserPageReq;
import com.iweb.entity.Products;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iweb.vo.IndexCategoryNamesVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2024-06-13
 */

@Mapper
public interface ProductsMapper extends BaseMapper<Products> {

    Page<IndexCategoryNamesVO> listProductsPage(Page<IndexCategoryNamesVO> page,
                                            @Param("request") ListProductsPageReq listProductsPageReq);
}
