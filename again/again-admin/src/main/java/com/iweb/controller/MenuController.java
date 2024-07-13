package com.iweb.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.iweb.config.Result;
import com.iweb.entity.ListProductsPageReq;
import com.iweb.entity.Menu;
import com.iweb.entity.Products;
import com.iweb.service.MenuService;
import com.iweb.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;


    @RequestMapping("/list")
    public Result getList(){
        List<Menu> list = menuService.list();
        //获取全部一级
        List<Menu> parentMenu = list.stream().filter(item -> item.getParent().intValue()==0).collect(Collectors.toList());
        //组装一级中的所有二级
        for (Menu menu : parentMenu) {
            List<Menu> children = list.stream().filter(item -> item.getParent().equals(menu.getId())).collect(Collectors.toList());
            if(CollectionUtils.isNotEmpty(children)){
                menu.setChildren(children);
            }
        }
        return Result.success(parentMenu);
    }

    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        Menu menu = menuService.getById(id);
        if(menu==null){
            return Result.failed(300,"菜单不存在");
        }
        if(menu.getParent()==0){
            //如果是父级菜单，判断下方是否有子菜单
            List<Menu> list = menuService.lambdaQuery().eq(Menu::getParent, id).list();
            if(CollectionUtils.isNotEmpty(list)){
                return Result.failed(300,"请先删除子菜单");
            }
        }
        menuService.removeById(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Menu menu){
        menuService.updateById(menu);
        return Result.success();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Menu menu){
        menuService.save(menu);
        return Result.success();
    }


    @RequestMapping("/getChildrenList")
    public Result getChildrenList(@RequestParam Integer id){
        List<Menu> list = menuService.lambdaQuery().eq(Menu::getParent, id).list();
        return Result.success(list);
    }

}
