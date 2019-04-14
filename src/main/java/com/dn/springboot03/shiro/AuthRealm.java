package com.dn.springboot03.shiro;


import com.dn.springboot03.entity.User;
import com.dn.springboot03.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    @Autowired
    UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("jsp验证");
        return null;
    }

    /**
     * 登陆-认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        try {
            System.out.println("登陆验证");
            UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
            String userName = token.getUsername();
            logger.info("userName="+userName);
            User user = userMapper.getUserByLoginName(userName);
            if(user != null){
                return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
