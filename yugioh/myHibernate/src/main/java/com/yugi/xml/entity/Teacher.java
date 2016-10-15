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
public class Teacher extends Person {

    private Integer salary;

    public Teacher(String name, int age, Integer salary) {
        super(name, age);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + ",Teacher{" +
                "salary=" + salary +
                '}';
    }
}
