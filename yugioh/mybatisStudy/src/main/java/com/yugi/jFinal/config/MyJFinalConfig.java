package com.yugi.jFinal.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.yugi.jFinal.ctrl.HelloController;
import com.yugi.jFinal.interceptor.TxInterceptor;
import com.yugi.jFinal.model.DeptModel;
import com.yugi.jFinal.model.EmpModel;

import java.util.Properties;

/**
 * Created by Administrator on 2017/1/26.
 */
public class MyJFinalConfig extends JFinalConfig {

    /**
     * 此方法用来配置常量值，如devMode（开发模式），viewType(视图类型)等
     *
     * @param constants
     */
    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setEncoding("utf-8");
        constants.setViewType(ViewType.JSP);
    }

    /**
     * 此方法用来配置访问路由
     *
     * @param routes
     */
    @Override
    public void configRoute(Routes routes) {
        // /hi路径默认是访问该类的index()方法,如果要访问其他方法可以用/方法名(),又或者使用@ActionKey
        // @ActionKey("/test")如果在该类方法中用了此注解,则访问路径就是注解上的路径,而不是hi/test
        routes.add("/hi", HelloController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    /**
     * 此方法用来配置plugin
     *
     * @param plugins
     */
    @Override
    public void configPlugin(Plugins plugins) {
        // loadPropertyFile("jdbc.properties");
        // C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbc.url"), getProperty("jdbc.username"), getProperty("jdbc.password"));
        PropKit.use("jdbc.properties");
        // 在configPlugin方法中添加：
        C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbc.url"), PropKit.get("jdbc.username"), PropKit.get("jdbc.password"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        arp.setShowSql(true);
        arp.setDevMode(true);
        // arp.addMapping("employ", EmpModel.class);
        arp.addMapping("dept", DeptModel.class);
        this.bindMapping(arp);
        plugins.add(c3p0Plugin);
        plugins.add(arp);


    }

    /**
     * 此方法用来配置intercept，在此处配置的拦截器将会对所有的请求进行拦截，除非使用@ClearInterceptor在Controller里清楚
     *
     * @param interceptors
     */
    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new TxInterceptor());
    }

    /**
     * 此方法用来配置Handle，handle可以接管所有web请求，并对应用拥有完全的控制权，可以很方便的实现更高层的功能性扩展
     *
     * @param handlers
     */
    @Override
    public void configHandler(Handlers handlers) {
        handlers.add(new ContextPathHandler("basePath"));
    }


    private void bindMapping(ActiveRecordPlugin arp) {
        try {
            Prop use = PropKit.use("clazz.properties");
            Properties prop = use.getProperties();
            for (Object o : prop.keySet()) {
                String key = o.toString();
                String value = prop.getProperty(key);
                Class clazz = Class.forName(value);
                arp.addMapping(key, clazz);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
