package com.iweb.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.iweb.config.Result;
import com.iweb.entity.*;
import com.iweb.enums.UserRoleEnum;
import com.iweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author zhangguohao
 * @date 2025/1/12 19:14
 */
@Service
public class LoginTestServiceImpl implements LoginTestService {


    @Autowired
    private CustomerService customerService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private AdminService adminService;
    /**
     * 登录,使用一个表进行三个角色登录
     *
     * @param loginDto 登录参数
     */
    @Override
    public Result loginForOneTable(LoginDto loginDto) {
        //判断用户信息是否为空
        if(StringUtil.isEmpty(loginDto.getUserName())){
            return Result.failed("用户名不能为空");
        }
        if(StringUtil.isEmpty(loginDto.getPassword())){
            return Result.failed("密码不能为空");
        }
        if(null == loginDto.getLoginType()){
            return Result.failed("登录类型不能为空");
        }
        //获取用户登录角色
        UserRoleEnum userRoleEnum = UserRoleEnum.getByCode(loginDto.getLoginType());
        //判断用户角色参数是否合规
        if(null == userRoleEnum){
            return Result.failed("用户角色选择异常");
        }
        //进行查询用户是否存在
        Customer customer = customerService.lambdaQuery()
                .eq(Customer::getUserName, loginDto.getUserName())
                .one();
        if(null == customer){
            return Result.failed("用户不存在");
        }
        //判断用户角色是否正确
        if(!customer.getRole().equals(userRoleEnum.getRoleCode())){
            return Result.failed("用户角色选择异常");
        }
        //判断用户密码是否正确
        if(!loginDto.getPassword().equals(customer.getPassword())){
            return Result.failed("密码不正确");
        }
        //生成token
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("login:token:"+token, JSON.toJSONString(customer));
        return Result.success(token);
    }

    /**
     * 登录，使用多个表，不同角色不同表
     *
     * @param loginDto 登录参数
     */
    @Override
    public Result loginForMuchTable(LoginDto loginDto) {
        //判断用户信息是否为空
        if(StringUtil.isEmpty(loginDto.getUserName())){
            return Result.failed("用户名不能为空");
        }
        if(StringUtil.isEmpty(loginDto.getPassword())){
            return Result.failed("密码不能为空");
        }
        if(null == loginDto.getLoginType()){
            return Result.failed("登录类型不能为空");
        }
        //获取用户登录角色
        UserRoleEnum userRoleEnum = UserRoleEnum.getByCode(loginDto.getLoginType());
        //判断用户角色参数是否合规
        if(null == userRoleEnum){
            return Result.failed("用户角色选择异常");
        }
        String password = null;
        String userJson = null;
        switch(userRoleEnum) {
            case SUPER_ADMIN:
                //查询超级管理员表，判断是否存在
                SuperAdmin superAdmin = superAdminService.lambdaQuery().eq(SuperAdmin::getUserName, loginDto.getUserName()).one();
                if(null == superAdmin){
                    return Result.failed("用户不存在");
                }
                //获取用户密码
                password = superAdmin.getPassword();
                //获取用户json数据，用于后续存放redis
                userJson = JSON.toJSONString(superAdmin);
                break;
            case ADMIN:
                //查询管理员表，判断是否存在
                Admin admin = adminService.lambdaQuery().eq(Admin::getUserName, loginDto.getUserName()).one();
                if(null == admin){
                    return Result.failed("用户不存在");
                }
                //获取用户密码
                password = admin.getPassword();
                //获取用户json数据，用于后续存放redis
                userJson = JSON.toJSONString(admin);
                break;
            case USER:
                //查询用户表，判断是否存在
                User user = userService.lambdaQuery().eq(User::getUserName, loginDto.getUserName()).one();
                if(null == user){
                    return Result.failed("用户不存在");
                }
                //获取用户密码
                password = user.getPassword();
                //获取用户json数据，用于后续存放redis
                userJson = JSON.toJSONString(user);
                break;
        }
        //判断密码是否正确
        if(!loginDto.getPassword().equals(password)){
            return Result.failed("密码不正确");
        }
        //生成token
        String token = UUID.randomUUID().toString().replace("-", "");
        //存储用户信息
        redisTemplate.opsForValue().set("login:token:"+token,userJson);
        return Result.success(token);
    }
}
