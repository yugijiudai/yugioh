package com.yugi.annotation.pojo;

import lombok.Data;

/**
 * Created by Administrator on 2016/10/13.
 */
@Data
public class MyVo {
    private String bName;

    private String cName;

    private Category2 category2;

    private Book2 book2;

    public MyVo() {
    }

    public MyVo(Category2 category2, Book2 book2) {
        this.category2 = category2;
        this.book2 = book2;
    }

}