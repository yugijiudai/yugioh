package com.yugi.annotation.pojo;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/1/11.
 */
@Entity
@Data
@Table(name = "t_bindGood")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BindGood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = Good.class)
    @JoinColumn(name = "good_id")
    // @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Good good;

    @ManyToOne(targetEntity = GoodTemplate.class)
    @JoinColumn(name = "goodTemplate_id")
    // @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private GoodTemplate goodTemplate;

}
