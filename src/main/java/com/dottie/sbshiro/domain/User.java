package com.dottie.sbshiro.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lindazhong
 * @date 2018/3/19 09:51
 */
@Data
public class User {

    private Long uid;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();
}
