package com.dottie.sbshiro.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lindazhong
 * @date 2018/3/19 09:54
 */
@Data
public class Role {

    private Long rid;

    private String rname;

    private Set<User> users = new HashSet<>();

    private Set<Permission> permissions = new HashSet<>();
}
