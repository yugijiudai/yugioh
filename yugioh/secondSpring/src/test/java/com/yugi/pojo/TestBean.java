package com.yugi.pojo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
//import javax.inject.Inject;

/**
 * Created by micarshowo1 on 2016/5/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring.xml"})
@ContextConfiguration(classes={BeanConfig.class})
public class TestBean {

    @Resource
    private Human student;

    @Resource
//    @Inject
    private Human customer;


    @Test
    public void testInject(){
        System.out.println(student);
        System.out.println(customer);
    }

    @Test
    public void testApplicationCtx(){
//        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.micar.pojo");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
        Human student = ctx.getBean("student",Student.class);
        System.out.println(student);
    }
}
