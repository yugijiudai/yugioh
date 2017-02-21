package com.yugi.bjsxt;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/10/25.
 */
@Entity
@Table(name = "t_msg")
@NoArgsConstructor
@Data
@ToString(exclude = "topic")
public class Msg {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String cont;

    @ManyToOne
    private Topic topic;

    public Msg(String cont, Topic topic) {
        this.cont = cont;
        this.topic = topic;
    }
}
