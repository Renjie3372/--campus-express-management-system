package com.campusexpress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campusexpress.entity.User;

public interface UserService extends IService<User> {
    User login(String account, String password);
}
