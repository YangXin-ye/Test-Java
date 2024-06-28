package com.iweb.controller;


import com.iweb.config.Result;
import com.iweb.service.DistrictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 省份区域信息表 前端控制器
 * </p>
 *
 * @author yxy
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/district")
public class DistrictController {

    @Resource
    private DistrictService districtService;

    /**
     * 获取省份区域信息
     *
     * @param districtId 区域id
     * @param level      区域级别
     * @return
     */
    @GetMapping("/listDistrict")
    public Result listDistrict(@RequestParam(value = "districtId", required = false) String districtId,
                               @RequestParam(value = "level", required = false) String level) {
        return Result.success(districtService.getDistrict(districtId, level));
    }

    /**
     * 根据id获取当前id下的区域信息以及当前id的区域名称
     *
     * @param districtId 区域id
     *
     */
    @GetMapping("/getInfo")
    public Result getInfo(@RequestParam(value = "districtId", required = false) String districtId) {

        return Result.success(districtService.getDistrictInfo(districtId));
    }

}
