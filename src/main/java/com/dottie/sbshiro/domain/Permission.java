package com.dottie.sbshiro.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lindazhong
 * @date 2018/3/19 10:00
 */
@Data
public class Permission {

    private Long mid;

    private String mname;

    private Set<Role> roles = new HashSet<>();
}
