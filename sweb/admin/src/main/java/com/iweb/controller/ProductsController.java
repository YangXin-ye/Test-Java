package com.iweb.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.config.Result;
import com.iweb.entity.AddProductsDTO;
import com.iweb.entity.ListProductsPageReq;
import com.iweb.entity.ListUserPageReq;
import com.iweb.entity.Products;
import com.iweb.service.ProductsService;
import com.iweb.vo.IndexCategoryNamesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yxy
 * @since 2024-06-13
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Resource
    private ProductsService productsService;


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String PRODUCTS_CACHE_KEY = "products_page";




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
        String cacheKey = PRODUCTS_CACHE_KEY + "_" + pageNum + "_" + pageSize;

        // 检查缓存
        Page<IndexCategoryNamesVO> productsPage;
        String redisData = redisTemplate.opsForValue().get(cacheKey);
        if ( redisData == null) {
            // 从数据库获取数据
            productsPage = productsService.listProductsPage(pageNum, pageSize, listProductsPageReq);

            // 存入缓存，设置缓存时间
            redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(productsPage), 10, TimeUnit.MINUTES);
        }else{
            productsPage = JSON.parseObject(redisData,Page.class);
        }
        return Result.success(productsPage);
    }

    /**
     * 增加数据
     *
     * @param products
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody AddProductsDTO addProductsDTO) {
        productsService.addProducts(addProductsDTO);
        clearCache();
        return Result.success();
    }

    /*
     ** 删
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        productsService.deleteOrder(id);
        clearCache();
        return Result.success();
    }

    /*
     ** 改
     */
    @PostMapping("/update")
    public Result update(@RequestBody Products products) {
        productsService.updateOrder(products);
        clearCache();
        return Result.success();
    }

    // 清除缓存的方法
    private void clearCache() {
        Set<String> keys = redisTemplate.keys(PRODUCTS_CACHE_KEY + "_*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }


    //
    // /*
    // ** 连表查询
    //  */
    // @GetMapping("/listAll")
    // public Result listAll() {
    //     return Result.success(productsService.listAll());
    // }

}
