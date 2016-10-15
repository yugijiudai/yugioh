package com.yugi.xml.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/23.
 */
@Getter
@Setter
// @Data
// @EqualsAndHashCode(exclude = {"students"})
@ToString(exclude = "students")
@NoArgsConstructor
public class Grade3 {

    private Integer id;

    private String name;

    private Map<String,Student3> students = new HashMap<>();

    public Grade3(String name) {
        this.name = name;
    }
}
