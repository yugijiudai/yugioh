package com.yugi.jFinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017/1/26.
 */
public class TxInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation invocation) {
        System.out.println("哇");
        try {
            Db.tx(new IAtom() {
                @Override
                public boolean run() throws SQLException {
                    invocation.invoke();
                    return true;
                }
            });
        }
        catch (Exception e) {
        }
    }
}
