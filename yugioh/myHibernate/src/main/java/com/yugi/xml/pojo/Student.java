package com.yugi.xml.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/18.
 */
@Getter
@Setter
// @Data
// @EqualsAndHashCode(exclude = {"grade"})
public class Student {

    private Integer id;

    private String name;

    private Integer age;

    //存放大数据,可以存放4G的内容
    private Blob image;

    private Clob introduce;

    private Grade grade;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", image=" + image +
                ", introduce=" + introduce +
                '}';
    }
}
