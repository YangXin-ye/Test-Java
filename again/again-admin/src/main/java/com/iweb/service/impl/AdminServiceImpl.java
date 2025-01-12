package com.iweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.entity.Admin;
import com.iweb.service.AdminService;
import com.iweb.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangguohao
* @description 针对表【admin(管理员表)】的数据库操作Service实现
* @createDate 2025-01-12 19:58:36
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




