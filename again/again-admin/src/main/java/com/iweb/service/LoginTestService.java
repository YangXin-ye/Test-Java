package com.iweb.service;

import com.iweb.config.Result;
import com.iweb.entity.LoginDto;

/**
 * @author zhangguohao
 * @date 2025/1/12 19:14
 */
public interface LoginTestService {
    /**
     * 登录，使用一个表进行三种角色登录
     * @param loginDto 登录参数
     */
    Result loginForOneTable(LoginDto loginDto);

    /**
     * 登录，使用多个表，不同角色不同表
     * @param loginDto 登录参数
     */
    Result loginForMuchTable(LoginDto loginDto);
}
