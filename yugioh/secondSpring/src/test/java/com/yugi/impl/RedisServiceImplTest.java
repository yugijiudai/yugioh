package com.yugi.impl;

import com.yugi.pojo.Emp;
import com.yugi.service.RedisService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-redis.xml"})
public class RedisServiceImplTest {

    @Resource
    private RedisService redisServiceImpl;

    private Emp emp;


    @Before
    public void init() {
        emp = new Emp();
        emp.setId(1);
        emp.setName("员工");
//        redisService = new RedisServiceImpl();
    }

    @Test
    public void get() throws Exception {
        String name = redisServiceImpl.get("name2");
        System.out.println(name);
        Emp emp = redisServiceImpl.get("com.yugi.impl.RedisServiceImplTest.del--id:1", Emp.class);
        System.out.println(emp);
        System.out.println(redisServiceImpl.get("com.yugi.impl.RedisServiceImplTest.del--id:1"));
    }


    @Test
    public void del() throws Exception {
        // 获得当前类名
        String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
        // 获得当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        redisServiceImpl.store(clazz + "." + method + "--id:1", emp);
        System.out.println("完成");
    }

    @Test
    public void set() throws Exception {
        redisServiceImpl.set("name2", "yugi2");
        System.out.println("完成");
    }


}