package com.dottie.sbshiro.mapper;

import com.dottie.sbshiro.domain.User;
import org.springframework.stereotype.Component;

/**
 * @author lindazhong
 * @date 2018/3/20 10:47
 */
@Component
public interface UserMapper {

    public User findUserByUsername(String username);

}
