package com.yugi.annotation.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by Administrator on 2016/10/10.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String pwd;

    //乐观锁版本
    @Version
    private int version;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }
}
