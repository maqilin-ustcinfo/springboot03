package com.dn.springboot03.dao.impl;

import com.dn.springboot03.dao.IProblemDao;
import com.dn.springboot03.entity.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProblemDao implements IProblemDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Problem getProblemById(Integer id) {
        String sql = "select id,content,summary from problem where id="+id;
        List<Problem> problemList = jdbcTemplate.query(sql, new RowMapper<Problem>() {
            @Override
            public Problem mapRow(ResultSet rs, int rowNum) throws SQLException {
                Problem problem = new Problem();
                problem.setId(rs.getInt("id"));
                problem.setContent(rs.getString("content"));
                problem.setSummary(rs.getString("summary"));
                return problem;
            }
        });
        if (problemList != null && !problemList.isEmpty()){
            return problemList.get(0);
        }else{
            return null;
        }
    }
}
