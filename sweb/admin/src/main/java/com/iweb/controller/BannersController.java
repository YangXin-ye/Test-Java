package com.iweb.controller;


import com.iweb.config.Result;
import com.iweb.service.BannersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxy
 * @since 2024-06-14
 */
@RestController
@RequestMapping("/banners")
public class BannersController {

    @Resource
    private BannersService bannersService;

    /*
    ** 查询全部轮播图
     */

    @GetMapping("/findAll")
    public Result findAll(){
        return Result.success(bannersService.findAll());
    }

}
