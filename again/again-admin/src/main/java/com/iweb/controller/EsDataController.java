package com.iweb.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.config.Result;
import com.iweb.entity.District;
import com.iweb.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author：
 * @Package：com.iweb.controller
 * @Project：Test-Java
 * @name：EsDataController
 * @Date：2024/7/2 17:08
 * @Description:
 */

@RestController
@RequestMapping("/es")
public class EsDataController {

    @Autowired
    private EsService esService;

    @GetMapping("/syncData")
    public Result syncData() {
        esService.syncData();
        return Result.success(true);
    }


    @GetMapping("/list")
    public Result list(@RequestParam(value = "",required = false) String name,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        Page<District> list = esService.list(name,pageNum,pageSize);
        return Result.success(list);
    }
}
