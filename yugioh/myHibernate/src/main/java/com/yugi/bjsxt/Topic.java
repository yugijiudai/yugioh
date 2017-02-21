package com.yugi.bjsxt;

import com.yugi.annotation.pojo.Book;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
@Entity
@Table(name = "t_topic")
@NoArgsConstructor
@Data
@NamedQueries({
        @NamedQuery(name = "topic.selectCertainTopic", query = "from Topic t where t.id = :id"),
        @NamedQuery(name = "topic.ss", query = "from Topic t where t.id = :id")}
)
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(targetEntity = Msg.class)
    @JoinColumn(name = "topic_id")
    // @OneToMany(mappedBy = "topic")
    private List<Msg> msgs = new ArrayList<>();

    private Date createDate;

    public Topic(String title, Date createDate) {
        this.title = title;
        this.createDate = createDate;
    }
}
