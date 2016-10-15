package com.yugi.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by micarshowo1 on 2016/5/25.
 */
@Configuration
//最好加上下面这个,不然@Resource的时候可能会冒红
//@ComponentScan(basePackages = "com.micar.pojo")
public class BeanConfig {


    @Bean(name = "student")
    public Human createStudent() {
        Student student = new Student();
        student.setName("学生");
        return student;
    }

    @Bean(name = "customer")
    public Human createCustomer() {
        return new Customer();
    }



}
