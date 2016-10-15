package com.yugi.xml.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2016/9/27.
 */
@Getter
@Setter
public class Person {
    private Integer id;

    private String name;

    private Integer age;

    private IdCard idCard;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
