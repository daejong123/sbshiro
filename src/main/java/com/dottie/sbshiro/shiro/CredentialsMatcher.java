package com.dottie.sbshiro.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author lindazhong
 * @date 2018/3/20 11:32
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String inPassword = String.valueOf(userToken.getPassword());
        String dbPassword = (String) info.getCredentials();
        System.out.println(inPassword + " " + dbPassword);
        return inPassword.equals(dbPassword);
    }
}
