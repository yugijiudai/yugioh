package com.yugi.dao;

import com.yugi.pojo.Emp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/5/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class EmpDaoImplTest {



    @Resource
    private EmpDao empDaoImpl;

    @Before
    public void setUp() throws Exception {
        System.out.println("==================");
    }

    @Test
    public void testUpdate() throws Throwable {
        Emp emp = new Emp();
        emp.setName("虎佛啊");
        empDaoImpl.update(emp);
    }
}