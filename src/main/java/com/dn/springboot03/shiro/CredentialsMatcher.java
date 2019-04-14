package com.dn.springboot03.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 密码校验
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        System.out.println("密码校验");
        UsernamePasswordToken uToke = (UsernamePasswordToken)token;
        // 用户输入的密码
        String password = new String(uToke.getPassword());
        String dbPawword = (String) info.getCredentials();
        // 比较
        return this.equals(password, dbPawword);
    }
}
