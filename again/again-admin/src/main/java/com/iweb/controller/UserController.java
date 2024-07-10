package com.iweb.controller;

import com.iweb.config.Result;
import com.iweb.entity.Users;
import com.iweb.service.UsersService;
import com.iweb.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/getLoginUserInfo")
    public Result getLoginUserInfo(@RequestHeader String token) {
        Claims claims = JwtUtils.parseJwt(token);
        String loginId = claims.get("id").toString();
        Users loginUser = usersService.getById(Integer.parseInt(loginId));
        return Result.success(loginUser);
    }

    @PostMapping("/upUserInfo")
    public Result upUserInfo(@RequestBody Users users) {
        usersService.updateById(users);
        return Result.success();
    }
}
