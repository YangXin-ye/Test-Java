package com.iweb.controller;

import com.iweb.config.Result;
import com.iweb.entity.Users;
import com.iweb.service.UsersService;
import com.iweb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 杨芯叶
 * @Create 2024-06-25-11:05
 * @Message
 **/
@Slf4j
@RestController
public class LoginController {

    @Resource
    private UsersService usersService;

    @PostMapping("/login")
    public Result login(@RequestBody Users users) {
        log.info("员工登录" + users.getName());
        Users u = usersService.login(users);
        // 登录失败或者成功
        // return e != null ? ResultFactory.success():ResultFactory.failed("用户名或者密码错误");
        if (u != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",u.getUId());
            claims.put("name",u.getName());
            claims.put("username",u.getUsername());

            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        // 登录失败
        return Result.failed("用户名或者密码错误");
    }
}
