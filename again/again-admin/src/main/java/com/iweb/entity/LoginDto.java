package com.iweb.entity;

import lombok.Data;

/**
 * @author zhangguohao
 * @date 2025/1/12 19:12
 */
@Data
public class LoginDto {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录类型 1普通用户 2超级管理员 3管理员
     */
    private Integer loginType;
}
