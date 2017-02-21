package com.yugi.annotation.pojo;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/1/11.
 */
@Entity
@Data
@Table(name = "t_good")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String goodName;


    public Good() {
    }

    public Good(String goodName) {
        this.goodName = goodName;
    }



    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", goodName='" + goodName + '\'' +
                '}';
    }
}
