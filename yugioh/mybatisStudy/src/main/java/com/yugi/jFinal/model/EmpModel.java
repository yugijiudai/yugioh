package com.yugi.jFinal.model;

import com.jfinal.aop.Before;
import com.yugi.jFinal.interceptor.TxInterceptor;

/**
 * Created by Administrator on 2017/1/26.
 */
@Before(TxInterceptor.class)
public class EmpModel extends BeanModel {

    public static final EmpModel empModel = new EmpModel();


    public void save2() throws Exception {
        EmpModel.empModel.findById(1).set("name", "xx").update();
        // Integer.parseInt("ffff");
    }
}
