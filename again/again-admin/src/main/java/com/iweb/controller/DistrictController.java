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
 * @since 2024-06-29
 */
@RestController
@RequestMapping("/district")
public class DistrictController {
    @Resource
    private DistrictService districtService;

    @GetMapping("/listDistrict")
    public Result listDistrict(@RequestParam(value = "districtId", required = false) String districtId,
                               @RequestParam(value = "level", required = false) String level) {
        return Result.success(districtService.getDistrict(districtId, level));
    }

}
