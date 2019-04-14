package com.dn.springboot03.service;

import com.dn.springboot03.entity.Problem;

public interface IProblemSerice {

    /**
     * 通过ID获取
     * @param id
     * @return
     */
    Problem getProblemById(Integer id);


    /**
     * 更新问题
     * @param id
     */
    void uptProblem(Integer id);

}
