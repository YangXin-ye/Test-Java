package com.iweb.controller;

import com.iweb.service.DynamicTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.iweb.config.Result;

import java.util.*;

/**
 * @Author：
 * @Package：com.iweb.controller
 * @Project：Test-Java
 * @name：DynamicTableController
 * @Date：2024/8/10 17:04
 * @Description:
 */
@RestController
@RequestMapping("/dynamicTable")
public class DynamicTableController {


    @Autowired
    private DynamicTableService dynamicTableService;

    @GetMapping("/list")
    public Result list(@RequestParam(required = true,defaultValue = "1") Integer pageNum,
                       @RequestParam(required = true,defaultValue = "5") Integer pageSize,
                       @RequestParam(required = false) String searchKey) {
        Map<String, Object> response = dynamicTableService.list(pageNum,pageSize,searchKey);
        return Result.success(response);
    }
}
