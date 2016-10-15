package com.yugi.xml.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Administrator on 2016/9/29.
 */
@Setter
@Getter
@NoArgsConstructor
public class Student extends Person {

    private String work;

    public Student(String name, int age, String work) {
        super(name, age);
        this.work = work;
    }

    @Override
    public String toString() {
        return super.toString() + ",Student{" +
                "work='" + work + '\'' +
                '}';
    }
}
