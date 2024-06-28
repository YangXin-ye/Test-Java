package com.iweb.controller;


import com.iweb.config.Result;
import com.iweb.entity.CategoryTempalte;
import com.iweb.entity.ListUserPageReq;
import com.iweb.service.CategoryTempalteService;
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
@RequestMapping("/category-tempalte")
public class CategoryTempalteController {

    @Resource
    private CategoryTempalteService categoryTempalteService;

    /*
     **  增加
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody CategoryTempalte categoryTempalte){
        categoryTempalteService.saveOrder(categoryTempalte);
        return Result.success();
    }

    /*
     ** 删
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        categoryTempalteService.deleteOrder(id);
        return Result.success();
    }

    /*
     ** 改
     */
    @PutMapping("/update")
    public Result update(@RequestBody CategoryTempalte categoryTempalte){
        categoryTempalteService.updateOrder(categoryTempalte);
        return Result.success();
    }


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
        return Result.success(categoryTempalteService.listUserPage(pageNum, pageSize, listUserPageReq));
    }

    /*
     ** 查询 categoryname 字段
     */

    @GetMapping("/categoryName")
    public Result categoryName(){
        return Result.success(categoryTempalteService.categoryName());
    }

}
