package com.yugi.annotation.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/30.
 * Entity:表示需要持久化的实体类
 * Table:实体类所对应的表
 * Id:主键
 * GeneratedValue:指定主键的生成策略
 * Column:列名
 */
@Entity
@Table(name = "t_book")
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
// 注意:如果Category没有@Cache,二级缓存会失效,一定要两个都写上,xml则不会
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "book_name")
    private String name;

    private Double price;

    private String author;

    private Date pubDate;

    // @ManyToOne(cascade = CascadeType.ALL)
    // 或者使用下面方式
    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Category category;


    public Book(String name, Double price, String author, Date pubDate) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.pubDate = pubDate;
    }

    public Book(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
