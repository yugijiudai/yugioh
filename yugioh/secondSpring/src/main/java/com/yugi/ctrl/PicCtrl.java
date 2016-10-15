package com.yugi.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/6/30.
 */
@Controller
public class PicCtrl {

    @RequestMapping(value = "pic")
    public String getPic(){
        return "emp/pic";
    }
}
