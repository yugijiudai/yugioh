package com.yugi.impl;

import com.yugi.dao.UserDao;
import com.yugi.pojo.User;
import com.yugi.service.IHelloService;
import org.directwebremoting.annotations.RemoteProxy;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/7/16.
 */
@RemoteProxy(name = "helloService")
public class HelloServiceImpl implements IHelloService {

    @Resource
    private UserDao userDao;

    @Override
//    @RemoteMethod
    public String sayHello(String name) {
        return userDao.sayHello(name);
    }

    @Override
//    @RemoteMethod
    public User load() {
        return new User(1, "abc");
    }
}
