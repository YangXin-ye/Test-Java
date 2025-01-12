package com.iweb.controller;

import com.iweb.config.Result;
import com.iweb.entity.LoginDto;
import com.iweb.entity.Users;
import com.iweb.enums.RoleEnum;
import com.iweb.service.LoginTestService;
import com.iweb.service.UsersService;
import com.iweb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangguohao
 * @Create 2024-06-28-16:16
 * @Message
 **/
@Slf4j
@RestController
public class LoginTestController {

    @Autowired
    private LoginTestService loginTestService;

    @PostMapping("/login/test")
    public Result login(@RequestBody LoginDto loginDto) {
        return loginTestService.loginForMuchTable(loginDto);
    }

}
