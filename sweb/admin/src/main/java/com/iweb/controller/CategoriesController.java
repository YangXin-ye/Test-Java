package com.iweb.controller;


import com.iweb.config.Result;
import com.iweb.entity.Categories;
import com.iweb.entity.ListUserPageReq;

import com.iweb.service.CategoriesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxy
 * @since 2024-06-13
 */
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Resource
    private CategoriesService categoriesService;


    /**
     * 分页查询所有数据
     *
     * @param listUserPageReq 请求体
     * @param pageNum         当前页
     * @param pageSize        每页大小
     */
    @GetMapping("/list")
    public Result listUserPage(ListUserPageReq listUserPageReq,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return Result.success(categoriesService.listUserPage(pageNum, pageSize, listUserPageReq));
    }

    /**
     * 增加数据
     * @param Categories
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Categories categories){
        categoriesService.addCategories(categories);
        return Result.success();
    }

    /*
     ** 删
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        categoriesService.deleteOrder(id);
        return Result.success();
    }

    /*
     ** 改
     */
    @PutMapping("/update")
    public Result update(@RequestBody Categories categories){
        categoriesService.updateOrder(categories);
        return Result.success();
    }
    /*
     ** 根据id查询内容
     */

    @GetMapping("/findAll/{pId}")
    public Result findAll(@PathVariable("pId") Integer pId){
        return Result.success(categoriesService.findAll(pId));
    }

    /*
     ** 连表查询数据
     */

    @GetMapping("/categoryNameByPId/{pId}")
    public Result categoryNameByPId(@PathVariable("pId") Integer pId){
        return Result.success(categoriesService.categoryNameByPId(pId));
    }



}
