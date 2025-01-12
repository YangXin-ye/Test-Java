package com.iweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.entity.Customer;
import com.iweb.service.CustomerService;
import com.iweb.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

/**
* @author zhangguohao
* @description 针对表【customer】的数据库操作Service实现
* @createDate 2025-01-12 19:22:50
*/
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
    implements CustomerService{

}




