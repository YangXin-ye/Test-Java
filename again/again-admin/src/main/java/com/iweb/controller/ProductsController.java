package com.iweb.controller;


import com.iweb.config.Result;
import com.iweb.entity.ListProductsPageReq;
import com.iweb.entity.Products;
import com.iweb.service.ProductsService;
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
@RequestMapping("/products")
public class ProductsController {

    @Resource
    private ProductsService productsService;

    /**
     * 分页查询所有数据
     *
     * @param listProductsPageReq 请求体
     * @param pageNum             当前页
     * @param pageSize            每页大小
     */
    @GetMapping("/list")
    public Result listUserPage(ListProductsPageReq listProductsPageReq,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        return Result.success(productsService.listUserPage(pageNum, pageSize, listProductsPageReq));
    }

    /**
     * 增加数据
     *
     * @param products
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Products products) {
        productsService.addProducts(products);
        return Result.success();
    }

    /*
     ** 删
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        productsService.deleteOrder(id);
        return Result.success();
    }

    /*
     ** 改
     */
    @PostMapping("/update")
    public Result update(@RequestBody Products products) {
        productsService.updateProduct(products);
        return Result.success();
    }


}
