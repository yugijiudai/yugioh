package com.yugi.bjsxt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by Administrator on 2016/12/9.
 */
@Entity
@Table(name = "t_xa")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Xa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer caId;

}
