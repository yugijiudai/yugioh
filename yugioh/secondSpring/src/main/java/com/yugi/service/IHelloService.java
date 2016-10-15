package com.yugi.service;

import com.yugi.pojo.User;

/**
 * Created by Administrator on 2016/7/16.
 */
public interface IHelloService {

    String sayHello(String name);

    User load();

}
