package com.iweb.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.config.Result;
import com.iweb.entity.ListUserPageReq;
import com.iweb.entity.Users;
import com.iweb.service.UsersService;
import com.iweb.vo.IndexCategoryNamesVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxy
 * @since 2024-06-07
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String USER_CACHE_KEY = "user_page";

    @GetMapping("/userInfo")
    public Result getUserInfo(){
        System.out.println("userInfo");
        return Result.success(usersService.getUserInfo());
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
        String cacheKey = USER_CACHE_KEY + "_" + pageNum + "_" + pageSize;

        // 检查缓存
        Page<Users> usersPage;
        String redisData = redisTemplate.opsForValue().get(cacheKey);
        if ( redisData == null) {
            usersPage = usersService.listUserPage(pageNum, pageSize, listUserPageReq);
            // 存入缓存，设置缓存时间
            redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(usersPage), 10, TimeUnit.MINUTES);
        }else{
            usersPage = JSON.parseObject(redisData,Page.class);
        }
        return Result.success(usersPage);
    }

    @PostMapping("/saveUser")
    public Result saveUser(@RequestBody Users users){
        usersService.saveUser(users);
        clearCache();
        return Result.success();
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody Users users){
        usersService.updateUser(users);
        clearCache();
        return Result.success();
    }

    @PostMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable("id") Integer id){
        usersService.deleteUser(id);
        clearCache();
        return Result.success();
    }


    private void clearCache() {
        Set<String> keys = redisTemplate.keys(USER_CACHE_KEY + "_*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }
}
