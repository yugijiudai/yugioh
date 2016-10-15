package com.yugi.impl;

import com.yugi.inject.InjSrv;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;


/**
 * Created by Administrator on 2016/4/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:inject.xml"})
public class InjSrvImplTest {

    @Inject
    private InjSrv injSrv;


    @Test
    public void testSay() throws Exception {
        System.out.println("==================");
        injSrv.say();

    }
}