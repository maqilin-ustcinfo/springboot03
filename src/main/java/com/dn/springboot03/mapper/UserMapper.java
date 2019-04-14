package com.dn.springboot03.mapper;

import com.dn.springboot03.entity.User;

public interface UserMapper {

    /**
     * 通过登录名查询用户
     * @param loginName
     * @return User
     */
    User getUserByLoginName(String loginName);

}
