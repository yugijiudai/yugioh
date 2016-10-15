package com.yugi.xml.pojo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/14.
 */
public class TestMe {

    public static void main(String[] args) {

    }

    @Test
    public void testHi(){
        Configuration cfg = new Configuration().configure();
        //hiberante3.x中是这种写法
        // SessionFactory sf = cfg.buildSessionFactory();
        // hibernate4.3
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory sf = cfg.buildSessionFactory(registry);
        Session session = sf.openSession();
        // 第一种写法
        Transaction tx = session.beginTransaction();
        // 第二种写法
        // Transaction tx = session.getTransaction();
        // tx.begin();
        User user = (User) session.get(User.class,2L);
        System.out.println(user);
        // User user = new User();
        // user.setName("ggg");
        // user.setPwd("ggg");
        // session.save(user);
        tx.commit();
        session.close();
        sf.close();
    }
}
