package com.yugi.annotation.pojo;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
@Log4j2
public class TwoCacheTest {

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


    /**
     * 二级缓存测试
     */
    @Test
    public void testTwoCacheGetLoad() {
        Book2 book2 = (Book2) session.get(Book2.class, 1);
        log.info(book2.getName());
        session.close();
        session = HibernateUtil.getSession();
        book2 = (Book2) session.get(Book2.class, 1);
        log.info(book2.getName());
    }

    /**
     * 查询缓存
     */
    @Test
    public void testTwoCacheQuery() {
        //setCacheable使用查询缓存
        List<Book2> list = session.createQuery("from Book2 ").setCacheable(true).list();
        log.info(list.size());
        session.close();
        session = HibernateUtil.getSession();
        list = session.createQuery("from Book2 ").setCacheable(true).list();
        log.info(list.size());
    }


}
