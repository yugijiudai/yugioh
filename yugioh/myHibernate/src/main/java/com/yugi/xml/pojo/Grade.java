package com.yugi.xml.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/23.
 */
@Getter
@Setter
// @Data
// @EqualsAndHashCode(exclude = {"students"})
public class Grade {

    private Integer id;

    private String name;

    private Set<Student> students = new HashSet<>();


    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
