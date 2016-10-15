package com.yugi.xml.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/27.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "roles")
public class Function {

    private Integer id;

    private String name;

    private String code;

    private String url;

    private Set<Role> roles = new HashSet<>();

    public Function(String name, String code, String url) {
        this.name = name;
        this.code = code;
        this.url = url;
    }
}
