package com.iweb.controller;


import com.iweb.config.Result;
import com.iweb.entity.Categories;
import com.iweb.entity.ListCategoriesPageReq;
import com.iweb.service.CategoriesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Resource
    private CategoriesService categoriesService;

    /**
     * 分页查询所有数据
     *
     * @param listCategoriesPageReq 请求体
     * @param pageNum             当前页
     * @param pageSize            每页大小
     */
    @GetMapping("/list")
    public Result listUserPage(ListCategoriesPageReq listCategoriesPageReq,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        return Result.success(categoriesService.listCategoryPage(pageNum, pageSize, listCategoriesPageReq));
    }

    /**
     * 增加数据
     *
     * @param categories
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Categories categories) {
        categoriesService.addCategory(categories);
        return Result.success();
    }

    /*
     ** 删
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        categoriesService.deleteCategory(id);
        return Result.success();
    }

    /*
     ** 改
     */
    @PostMapping("/update")
    public Result update(@RequestBody Categories categories) {
        categoriesService.updateCategory(categories);
        return Result.success();
    }

}
