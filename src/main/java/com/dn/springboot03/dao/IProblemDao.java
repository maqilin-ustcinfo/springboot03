package com.dn.springboot03.dao;

import com.dn.springboot03.entity.Problem;

public interface IProblemDao {

    /**
     * 通过ID查询问题
     * @param id
     * @return
     */
    Problem getProblemById(Integer id);
}
