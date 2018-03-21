package com.dottie.sbshiro.shiro;

import com.dottie.sbshiro.domain.Permission;
import com.dottie.sbshiro.domain.Role;
import com.dottie.sbshiro.domain.User;
import com.dottie.sbshiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lindazhong
 * @date 2018/3/20 11:22
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //认证，登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.findUserByUsername(username);
        if (null == user) {
            throw new UnknownAccountException("帐号不正确！");
        }
        //放入shiro.调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user=(User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions=new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if(roles.size()>0) {
            for(Role role : roles) {
                Set<Permission> modules = role.getPermissions();
                if(modules.size()>0) {
                    for(Permission module : modules) {
                        permissions.add(module.getMname());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //将权限放入shiro中.
        info.addStringPermissions(permissions);
        return info;
    }
}

/**
 * 授权的方法是在碰到<shiro:hasPermission>标签的时候调用的,
 * 它会去检测shiro框架中的权限(这里的permissions)是否包含有该标签的name值,
 * 如果有,里面的内容显示,如果没有,里面的内容不予显示(这就完成了对于权限的认证.)
 */
