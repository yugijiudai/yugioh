package com.yugi.dao;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;

/**
 * Created by Administrator on 2016/7/16.
 * name="userDao"对应页面的dwr.engine._execute('dwr', 'userDao', 'sayHello', ['wtf!', function (data) {});
 *
 */
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "userDao"),name="userDao")
public class UserDao {

    @RemoteMethod
    public String sayHello(String name) {
        return "UserDaohello " + name;
    }
}
