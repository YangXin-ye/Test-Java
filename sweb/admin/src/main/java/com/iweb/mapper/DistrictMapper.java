package com.iweb.mapper;

import com.iweb.entity.District;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 省份区域信息表 Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2024-06-08
 */
@Mapper
public interface DistrictMapper extends BaseMapper<District> {
    List<District> getDistrictList(@Param("districtId") String districtId);
}
