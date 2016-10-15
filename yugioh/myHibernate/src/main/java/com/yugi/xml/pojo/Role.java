package com.yugi.xml.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/27.
 */
@Setter
@Getter
@ToString(exclude = "functions")
public class Role {

    private Integer id;

    private String name;

    private Set<Function> functions = new HashSet<>();


}
