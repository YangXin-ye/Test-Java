package com.iweb.service;

import com.iweb.entity.District;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.swing.plaf.synth.Region;
import java.util.List;

/**
 * <p>
 * 省份区域信息表 服务类
 * </p>
 *
 * @author yxy
 * @since 2024-06-08
 */
public interface DistrictService extends IService<District> {


    /**
     * 获取省份区域信息
     *
     * @param districtId 区域id
     * @param level      区域级别
     */
    List<District> getDistrict(String districtId, String level);


    /**
     * 获取id对应的区域信息
     *
     * @param districtId 区域id
     */
    List<District> getDistrictInfo(String districtId);
}
