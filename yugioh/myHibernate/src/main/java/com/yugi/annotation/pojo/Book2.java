package com.yugi.annotation.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by Administrator on 2016/10/8.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "category2")
public class Book2 {

    private Integer id;

    private String name;

    private Double price;

    private String author;

    private Date pubDate;

    private Category2 category2;

    public Book2(String name, Double price, String author, Date pubDate) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.pubDate = pubDate;
    }

    public Book2(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
