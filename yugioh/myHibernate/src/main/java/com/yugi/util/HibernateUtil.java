package com.yugi.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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
            session = factory.openSession();
            return session;
        }
        return null;
    }

    public static void closeSession() {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
