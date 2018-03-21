package com.dottie.sbshiro.service.impl;

import com.dottie.sbshiro.domain.User;
import com.dottie.sbshiro.mapper.UserMapper;
import com.dottie.sbshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lindazhong
 * @date 2018/3/20 11:19
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
}
