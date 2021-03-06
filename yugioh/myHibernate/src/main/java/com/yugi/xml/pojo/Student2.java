package com.yugi.xml.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;
import java.sql.Clob;

/**
 * Created by Administrator on 2016/9/18.
 */
@Getter
@Setter
// @Data
// @EqualsAndHashCode(exclude = {"grade"})
@ToString(exclude = "grade")
@NoArgsConstructor
public class Student2 {

    private Integer id;

    private String name;

    private Integer age;

    private Grade2 grade;

    public Student2(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
