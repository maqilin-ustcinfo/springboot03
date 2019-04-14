package com.dn.springboot03.service.impl;

import com.dn.springboot03.entity.Problem;
import com.dn.springboot03.mapper.ProblemMapper;
import com.dn.springboot03.service.IProblemSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class ProblemService implements IProblemSerice {

    @Autowired
    ProblemMapper problemMapper;

    @Override
    public Problem getProblemById(Integer id) {
        return problemMapper.getProblemById(id);
    }

    @Override
    @Transactional
    public void uptProblem(Integer id) {
        Problem problem = problemMapper.getProblemById(id);
        Random r = new Random();
        problem.setContent(r.nextInt(100)+"");
        problemMapper.updateProblemById(problem);
    }

}
