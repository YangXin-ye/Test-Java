package com.iweb.controller;


import com.iweb.config.Result;
import com.iweb.entity.CategoryTempalte;
import com.iweb.entity.ListCategoriesPageReq;
import com.iweb.service.CategoryTempalteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */
@RestController
@RequestMapping("/category-tempalte")
public class CategoryTemplateController {

    @Resource
    private CategoryTempalteService categoryTempalteService;

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

        return Result.success(categoryTempalteService.listUserPage(pageNum, pageSize, listCategoriesPageReq));
    }

    /**
     * 增加数据
     *
     * @param categoryTempalte
     * @return
     */
    @PostMapping("/insert")
    public Result add(@RequestBody CategoryTempalte categoryTempalte) {
        categoryTempalteService.addProducts(categoryTempalte);
        return Result.success();
    }

    /*
     ** 删
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        categoryTempalteService.deleteOrder(id);
        return Result.success();
    }

    /*
     ** 改
     */
    @PostMapping("/update")
    public Result update(@RequestBody CategoryTempalte categoryTempalte) {
        categoryTempalteService.updateProduct(categoryTempalte);
        return Result.success();
    }

    /**
     * 获取所有分类名称
     * @return
     */
    @GetMapping("/categoryName")
    public Result categoryName() {
        List<CategoryTempalte> list = categoryTempalteService.list();
        return Result.success(list);
    }

}
