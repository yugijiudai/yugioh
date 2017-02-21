package com.yugi.dao;

import com.yugi.pojo.Emp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/5/20.
 */
@Repository
public class EmpDaoImpl implements EmpDao {

    /**
     * fe
     */
    public static int flag = 1;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp) {
        String sql = "insert into emp set name = ?";
        jdbcTemplate.update(sql, new Object[]{emp.getName()});
        // Integer.parseInt("fefew");
    }


}
