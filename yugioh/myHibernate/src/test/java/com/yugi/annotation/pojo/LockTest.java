package com.yugi.annotation.pojo;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.LockOptions;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
@Log4j2
public class LockTest {

    private Session session;

    private Transaction tx;

    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
    }

    @After
    public void setDown() throws Exception {
        HibernateUtil.closeSession();
    }

    @Test
    public void testSaveUser() {
        User user1 = new User("龟爷", "123");
        User user2 = new User("虎佛", "123");
        User user3 = new User("飒爽", "123");
        session.save(user1);
        session.save(user2);
        session.save(user3);
        tx.commit();
    }

    @Test
    public void testPessimisticLock1() {
        User user = (User) session.get(User.class, 1, LockOptions.UPGRADE);
        log.info(user.getName());
        // user.setName("aaa");
        // session.saveOrUpdate(user);
        tx.commit();
    }

    @Test
    public void testPessimisticLock2() {
        User user = (User) session.get(User.class, 1, LockOptions.UPGRADE);
        log.info(user.getName());
        // user.setName("bbbb");
        // session.saveOrUpdate(user);
        tx.commit();
    }


    @Test
    public void testPessimisticLock3() {
        String hql = "select * from t_user where id = 1";
        SQLQuery sqlQuery = session.createSQLQuery(hql);
        // sqlQuery.addScalar("id", StandardBasicTypes.INTEGER).addScalar("version", StandardBasicTypes.STRING);
        // sqlQuery.setResultTransformer(Transformers.aliasToBean(User.class));
        // sqlQuery.setResultTransformer(Transformers.aliasToBean(User.class));
        sqlQuery.addEntity(User.class);
        User user = (User) sqlQuery.uniqueResult();
        String updateSql = "update t_user set name = '中断',version = version + 1 where id = 1 and version = ? ";
        SQLQuery sqlQuery2 = session.createSQLQuery(updateSql);
        sqlQuery2.setParameter(0, user.getVersion());
        sqlQuery2.executeUpdate();
        System.out.println();
        tx.commit();
    }

    @Test
    public void testPessimisticLock4() {
        String hql = "select * from t_user where id > 1";
        SQLQuery sqlQuery = session.createSQLQuery(hql);
        // sqlQuery.addScalar("id", StandardBasicTypes.INTEGER).addScalar("version", StandardBasicTypes.STRING);
        // sqlQuery.setResultTransformer(Transformers.aliasToBean(User.class));
        sqlQuery.addEntity(User.class);
        User user = (User) sqlQuery.uniqueResult();
        String updateSql = "update t_user set name = '成功',version = version + 1 where id = 1 and version = ? ";
        SQLQuery sqlQuery2 = session.createSQLQuery(updateSql);
        sqlQuery2.setParameter(0, user.getVersion());
        sqlQuery2.executeUpdate();
        tx.commit();
    }


    @Test
    public void testOptimisticLock1() {
        User user = (User) session.get(User.class, 1);
        user.setName("呵呵1");
        tx.commit();
    }

    @Test
    public void testOptimisticLock2() {
        User user = (User) session.get(User.class, 1);
        user.setName("呵呵2");
        tx.commit();
    }

    @Test
    public void testOptimisticLock3() {
        String name = "注入',pwd = 'hehe";
        String updateSql = "update t_user set name = '" + name + "' where id > 1 ";
        SQLQuery sqlQuery2 = session.createSQLQuery(updateSql);
        sqlQuery2.executeUpdate();
        tx.commit();
    }



}
