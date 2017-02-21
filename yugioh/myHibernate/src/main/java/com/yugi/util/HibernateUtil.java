package com.yugi.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Created by Administrator on 2016/9/18.
 */
public class HibernateUtil {

    private static Configuration cfg = null;
    private static SessionFactory factory = null;
    private static Session session = null;

    static {
        cfg = new Configuration().configure();
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        factory = cfg.buildSessionFactory(registry);
    }

    public static Session getSession() {
        if (factory != null) {
            return session = factory.openSession();
        }
        return null;
    }

    public static void closeSession() {
        if (session != null && session.isOpen()) {
            session.close();
        }
        if (factory != null) {
            factory.close();
        }
    }


    public static void CreateDB() {
        Configuration cfg = new Configuration().configure();
        SchemaExport se = new SchemaExport(cfg);
        // 第一个参数是否生成ddl脚本,第二个参数是否执行到数据库中
        se.create(true, true);
    }
}
