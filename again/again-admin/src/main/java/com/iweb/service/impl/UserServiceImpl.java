package com.iweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.entity.User;
import com.iweb.service.UserService;
import com.iweb.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangguohao
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-01-12 19:58:36
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




