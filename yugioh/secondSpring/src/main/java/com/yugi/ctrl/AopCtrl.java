package com.yugi.ctrl;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/7/5.
 */
@Service
public class AopCtrl {

    public void first(){
        System.out.println("first");
    }

    public void second(){
        System.out.println("second");
    }

}
