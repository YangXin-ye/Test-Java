package com.iweb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.entity.District;
import com.iweb.mapper.DistrictMapper;
import com.iweb.service.DistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 省份区域信息表 服务实现类
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements DistrictService {

   @Resource
   private DistrictMapper districtMapper;

        @Override
        public List<District> getDistrict(String districtId, String level) {
            if (StrUtil.isAllBlank(districtId, level)) {
                throw new RuntimeException("id和level不能同时为空");
            }
            // 如果传递id，则查询该id下的下一级
            if (StrUtil.isNotBlank(districtId)) {
                return this.list(Wrappers.<District>lambdaQuery().eq(District::getParentId, districtId));
            }
            // 如果传递level，则查询该level对应的数据
            return this.list(Wrappers.<District>lambdaQuery().eq(District::getLevel, level));
        }
    }

