package com.yugi.annotation.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/30.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "books2")
public class Category2 {

    private Integer id;

    private String name;

    private Set<Book2> books2 = new HashSet<>();

    public Category2(String name) {
        this.name = name;
    }
}
