package com.yugi.annotation.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/30.
 */
@Entity
@Table(name = "t_category")
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "books")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob
    private String name;

    // 不加mappedBy会多一张中间表!!!,这个category对应Book的category属性,但这样写只能由Book维护,即插入category,book的category_id都不会有值,
    // mappedBy不能与JoinColumn共用
    // @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE)
    // 或者使用以下的3个方法,谁维护都可以,如果有JoinColumn,则targetEntity可写可不写;如果没有又不写targetEntity,则会多一张中间表！！！
    @OneToMany(targetEntity = Book.class)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "category_id")
    private Set<Book> books = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }
}
