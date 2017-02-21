package com.yugi.annotation.pojo;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */
@Entity
@Data
@Table(name = "t_goodTemplate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GoodTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String templateName;

    @OneToMany(targetEntity = BindGood.class)
    // @Cascade(value = CascadeType.ALL)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "goodTemplate_id")
    private List<BindGood> bindGoods = new ArrayList<>();


}
