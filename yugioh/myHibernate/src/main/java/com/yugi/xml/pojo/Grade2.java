package com.yugi.xml.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/23.
 */
@Getter
@Setter
// @Data
// @EqualsAndHashCode(exclude = {"students"})
@ToString(exclude = "students")
@NoArgsConstructor
public class Grade2 {

    private Integer id;

    private String name;

    private List<Student2> students = new ArrayList<>();

    public Grade2(String name) {
        this.name = name;
    }
}
