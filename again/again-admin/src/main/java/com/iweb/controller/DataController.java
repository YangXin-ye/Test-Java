package com.iweb.controller;


import com.iweb.config.Result;
import com.iweb.entity.Datas;
import com.iweb.entity.ListDataPageReq;
import com.iweb.service.DataService;
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
@RequestMapping("/data")
public class DataController {

    @Resource
    private DataService dataService;

    /*
     **  增加
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Datas data){
        dataService.saveData(data);
        return Result.success();
    }

    /*
     ** 删
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        dataService.deleteData(id);
        return Result.success();
    }

    /*
     ** 改
     */
    @PostMapping("/update")
    public Result update(@RequestBody Datas data){
        dataService.updateData(data);
        return Result.success();
    }


    /*
     **  查询全部数据
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        return Result.success(dataService.selectAll());
    }

    /**
     * 分页查询所有数据
     *
     * @param listDataPageReq 请求体
     * @param pageNum         当前页
     * @param pageSize        每页大小
     */
    @GetMapping("/list")
    public Result listUserPage(ListDataPageReq listDataPageReq,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return Result.success(dataService.listDataPage(pageNum, pageSize, listDataPageReq));
    }


}
