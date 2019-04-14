package com.dn.springboot03.service.impl;

import com.dn.springboot03.entity.Problem;
import com.dn.springboot03.service.IProblemSerice;
import org.springframework.stereotype.Service;

@Service
public class ProblemService2 implements IProblemSerice {

    @Override
    public Problem getProblemById(Integer id) {
        System.out.println("ProblemService2.getProblemById");
        return null;
    }

    @Override
    public void uptProblem(Integer id) {
    }
}
