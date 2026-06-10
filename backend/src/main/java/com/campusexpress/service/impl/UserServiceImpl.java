package com.campusexpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campusexpress.entity.User;
import com.campusexpress.mapper.UserMapper;
import com.campusexpress.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(String account, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account).eq("password", password);
        return baseMapper.selectOne(wrapper);
    }
}
