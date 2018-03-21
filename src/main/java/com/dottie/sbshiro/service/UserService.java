package com.dottie.sbshiro.service;

import com.dottie.sbshiro.domain.User;

/**
 * @author lindazhong
 * @date 2018/3/20 11:18
 */
public interface UserService {

    public User findUserByUsername(String username);
}
