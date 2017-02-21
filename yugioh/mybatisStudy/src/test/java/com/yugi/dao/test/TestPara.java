package com.yugi.dao.test;

import com.yugi.service.EmpSrv;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author yugi
 * @apiNote
 * @since 2017-02-17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
// @RunWith(Parameterized.class)
public class TestPara {

    @Resource
    protected EmpSrv empSrv;

    @Test
    public void test2(){
        System.out.println(empSrv);
    }

}
