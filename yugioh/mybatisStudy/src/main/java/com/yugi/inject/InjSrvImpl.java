package com.yugi.inject;


import javax.inject.Named;

/**
 * Created by Administrator on 2016/4/19.
 */
@Named
public class InjSrvImpl implements InjSrv {
    @Override
    public void say() {
        System.out.println("say");
    }
}
