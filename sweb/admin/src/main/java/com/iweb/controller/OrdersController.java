package com.iweb.controller;


import com.iweb.config.Result;
import com.iweb.entity.ListUserPageReq;
import com.iweb.entity.Orders;
import com.iweb.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxy
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    /*
    **  增加
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Orders orders){
        ordersService.saveOrder(orders);
        return Result.success();
    }

    /*
    ** 删
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        ordersService.deleteOrder(id);
        return Result.success();
    }

    /*
    ** 改
     */
    @PutMapping("/update")
    public Result update(@RequestBody Orders orders){
        ordersService.updateOrder(orders);
        return Result.success();
    }


    /*
    **  查询全部数据
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        return Result.success(ordersService.selectAll());
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
        return Result.success(ordersService.listUserPage(pageNum, pageSize, listUserPageReq));
    }



}
