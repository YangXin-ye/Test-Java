package com.iweb.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.iweb.entity.District;
import com.iweb.mapper.DistrictMapper;
import com.iweb.service.DistrictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.plaf.synth.Region;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 省份区域信息表 服务实现类
 * </p>
 *
 * @author yxy
 * @since 2024-06-08
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

    @Override
    public List<District> getDistrictInfo(String districtId) {
        if (StrUtil.isAllBlank(districtId)) {
            throw new RuntimeException("id不能为空");
        }

        // 传递id，查询该id下的下一级以及当前id对应的数据
        List<District> districtList = districtMapper.getDistrictList(districtId);
        List<District> returnList = new ArrayList();
        List<String> tempList = districtList.stream().map(District::getId).collect(Collectors.toList());
        for (District item : districtList) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(item.getParentId())) {
                recursionFn(districtList, item);
                returnList.add(item);
            }
        }

        if (returnList.isEmpty()) {
            returnList = districtList;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<District> list, District t) {
        // 得到子节点列表
        List<District> childList = getChildList(list, t);
        t.setChildren(childList);
        for (District tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<District> getChildList(List<District> list, District t) {
        List<District> tlist = new ArrayList<District>();
        Iterator<District> it = list.iterator();
        while (it.hasNext()) {
            District n = (District) it.next();
            if (n.getParentId() !=null && n.getParentId().equals(t.getId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<District> list, District t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }




}
