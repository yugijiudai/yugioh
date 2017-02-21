package com.yugi.dao;

import com.yugi.pojo.Emp;
import com.yugi.service.IHelloService;
import org.junit.Assert;
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
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-redis.xml"})
// @ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-redis.xml","classpath:spring-web.xml"})
public class EmpDaoImplTest {


    @Resource
    private EmpDao empDaoImpl;

    @Resource
    private IHelloService helloServiceImpl;


    @Before
    public void setUp() throws Exception {
        System.out.println("==================");
    }

    @Test
    public void testSayHello() {
        String fff = helloServiceImpl.sayHello("fff");
        System.out.println(fff);

    }

    @Test
    public void testUpdate() throws Throwable {
        Emp emp = new Emp();
        emp.setName("虎佛啊");
        empDaoImpl.update(emp);
    }
}