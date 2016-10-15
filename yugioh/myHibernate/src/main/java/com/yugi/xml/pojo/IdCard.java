package com.yugi.xml.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2016/9/27.
 */
@Getter
@Setter
public class IdCard {

    private Integer id;

    private String code;

    private Person person;

    @Override
    public String toString() {
        return "IdCard{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
