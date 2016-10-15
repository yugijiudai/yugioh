package com.yugi.dao;

import com.yugi.entity.Emp;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 * 无xml配置文件
 */
public interface EmpMapper {
    @Select("select * from emp")
    List<Emp> queryForEmp();
}
