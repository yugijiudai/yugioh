package com.yugi.bjsxt;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2016/10/25.
 */
@Data
@NoArgsConstructor
public class MsgInfo {


    private Integer id;

    private String cont;

    private String topicName;

    private String categoryName;

    public MsgInfo(Integer id, String cont, String topicName, String categoryName) {
        this.id = id;
        this.cont = cont;
        this.topicName = topicName;
        this.categoryName = categoryName;
    }
}
