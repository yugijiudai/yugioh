package com.yugi.xml.pojo;

import com.yugi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/18.
 */
public class HibernateTest {


    @Test
    public void testSave() {
        Session session;
        Transaction tx = null;
        User user;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            //构造对象-瞬时状态
            user = new User();
            user.setName("战三");
            user.setPwd("密码");
            //持久状态,user被session管理,并且有id有值--oid
            session.save(user);
            // 在持久状态下,脏数据检查:当提交事务或者清理缓存时发现session中数据和数据库中数据不一致时，
            // 将会把session中的数据更新到数据库中
            user.setName("李四");
            // 在保存以后再修改对象,那么将会产生多条sql语句,那么效率较低,建议在save前修改
            tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
        finally {
            HibernateUtil.closeSession();
        }
        //user:游离状态
        System.out.println(user.getName());
        user.setName("呵呵");
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        //持久状态
        session.update(user);
        tx.commit();
    }

    @Test
    public void testGet() {
        Session session;
        Transaction tx = null;
        User user;
        try {
            session = HibernateUtil.getSession();
            // get后变为持久状态,会立即查询该对象,范围从session,sessionFactory,数据库中,如果找不到对象,不会抛异常,返回null
            user = (User) session.get(User.class, 1L);
            tx = session.beginTransaction();
            // System.out.println(user);
            // clear清除session缓存中所有对象,evict清除指定对象
            tx.commit();
            session.clear();
            // 游离状态,不被session管理,数据库中不会被更改
            user.setName("你啊");
            System.out.println(user.getName());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void testLoad() {
        Session session;
        Transaction tx;
        User user;
        try {
            session = HibernateUtil.getSession();
            // load不会立即查询对象,到使用的时候才会查询
            user = (User) session.load(User.class, 1L);
            // System.out.println(user);
            System.out.println("完成");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            HibernateUtil.closeSession();
        }
    }

    @Test
    public void testDelete() {
        Session session;
        Transaction tx;
        User user;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            // user = new User();
            // user.setId(12L);
            // user.setName("dsfe");
            //手动构造一个对象,指定其主键是可以删除该对象的,但是不建议这么用
            user = (User) session.get(User.class, 12L);
            if (user != null) {
                session.delete(user);
            }
            tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            HibernateUtil.closeSession();
        }
    }


    @Test
    public void testUpdate() {
        Session session;
        Transaction tx;
        User user;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            // user = new User();
            // user.setId(1L);
            // user.setName("dsfe");
            // 手动构造的对象也可以修改,但是需要指定素有属性，不建议使用
            user = (User) session.get(User.class, 1L);
            user.setName("更新后");
            if (user != null) {
                session.update(user);
            }
            tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            HibernateUtil.closeSession();
        }
    }


}
