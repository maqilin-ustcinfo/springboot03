package com.dn.springboot03.mapper;

import com.dn.springboot03.entity.Problem;

public interface ProblemMapper {

   /**
    * 通过ID查询问题
    * @param id
    * @return
    */
   Problem getProblemById(Integer id);

   /**
    * 更新问题
    * @param problem
    */
   void updateProblemById(Problem problem);

}
