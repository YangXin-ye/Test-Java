package com.iweb.controller;

import com.iweb.config.Result;
import com.iweb.entity.ListProductsPageReq;
import com.iweb.service.SqlOptimizeService;
import com.iweb.vo.SqlOptimizeVo;
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
 * @name：SqlOptimizeController
 * @Date：2024/7/15 12:41
 * @Description:
 */
@RestController
@RequestMapping("/sqlOpt")
public class SqlOptimizeController {

    @Autowired
    private SqlOptimizeService sqlOptimizeService;

    @GetMapping("/list1")
    public Result list() {
        //limit 优化前
        long startTime = System.currentTimeMillis();
        sqlOptimizeService.list();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return Result.success(duration);
    }
    @GetMapping("/list2")
    public Result list2() {
        //limit 优化后
        long startTime = System.currentTimeMillis();
        sqlOptimizeService.list2();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return Result.success(duration);
    }

    @GetMapping("/list3")
    public Result list3() {
        //单个索引优化
        long startTime = System.currentTimeMillis();
        sqlOptimizeService.list3();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return Result.success(duration);
    }

    @GetMapping("/list4")
    public Result list4() {
        //复合索引优化
        long startTime = System.currentTimeMillis();
        sqlOptimizeService.list4();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return Result.success(duration);
    }
    @GetMapping("/list5")
    public Result list5() {
        //函数 优化前
        long startTime = System.currentTimeMillis();
        sqlOptimizeService.list5();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return Result.success(duration);
    }
    @GetMapping("/list6")
    public Result list6() {
        //函数 优化后
        long startTime = System.currentTimeMillis();
        sqlOptimizeService.list6();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return Result.success(duration);
    }

    @GetMapping("/list7")
    public Result list7() {
        //减少回表 优化后
        long startTime = System.currentTimeMillis();
        sqlOptimizeService.list7();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return Result.success(duration);
    }
}
