package com.yugi.xml.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Administrator on 2016/9/29.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Person {

    private Integer id;

    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
