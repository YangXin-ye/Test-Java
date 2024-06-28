package com.iweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import com.iweb.entity.ListUserPageReq;
import com.iweb.entity.Users;
import com.iweb.mapper.UsersMapper;
import com.iweb.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iweb.vo.IndexUserInfoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yxy
 * @since 2024-06-07
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public IndexUserInfoVO getUserInfo() {
        // todo 获取当前登录用户
        IndexUserInfoVO userInfo = usersMapper.getUserInfo("1");
        return userInfo;
    }

    @Override
    public Page<Users> listUserPage(Integer pageNum, Integer pageSize, ListUserPageReq listUserPageReq) {
        LambdaQueryWrapper<Users> lambdaQuery = Wrappers.<Users>lambdaQuery();
        lambdaQuery.like(StrUtil.isNotBlank(listUserPageReq.getUsername()), Users::getUsername, listUserPageReq.getUsername());

        Page<Users> page = new Page<>(pageNum, pageSize);
        Page<Users> resultPage = baseMapper.selectPage(page, lambdaQuery);
        return resultPage;
    }

    @Override
    public void saveUser(Users users) {
        // todo 检查用户信息
        baseMapper.insert(users);
    }

    @Override
    public void updateUser(Users users) {
        baseMapper.updateById(users);
    }

    @Override
    public void deleteUser(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public Users login(Users users) {
        return usersMapper.getByUserNameAndPassword(users);
    }

}
