package com.iweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iweb.entity.District;

import java.util.List;

/**
 * <p>
 * 省份区域信息表 服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
public interface DistrictService extends IService<District> {

    List<District> getDistrict(String districtId, String level);
}
