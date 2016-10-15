package com.yugi.pojo;

import lombok.Data;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * Created by Administrator on 2016/9/11.
 */
@Data
@Log4j2
public class St {

    // @Getter
    private String name;

    // @Setter(AccessLevel.PROTECTED)
    private Integer id;

    private boolean isOk;

    public void testLog(){
        log.info("名字是{}",this.name);
    }

}
