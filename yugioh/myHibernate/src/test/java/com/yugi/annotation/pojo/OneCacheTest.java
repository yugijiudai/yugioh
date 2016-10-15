package com.yugi.annotation.pojo;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
@Log4j2
public class OneCacheTest {

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
     * get和load会查询缓存,也会放进缓存
     */
    @Test
    public void testOneCacheGetLoad() {
        Book2 book2 = (Book2) session.get(Book2.class, 1);
        log.info(book2.getName());
        // session.close();
        // session = HibernateUtil.getSession();
        Book2 book21 = (Book2) session.get(Book2.class, 1);
        log.info(book21.getName());
    }

    /**
     * list查询不会查缓存,但list查询的实体对象将会放入缓存中
     */
    @Test
    public void testOneCacheList1() {
        List<Book2> list = session.createQuery("from Book2 ").list();
        log.info(list.size());
        list = session.createQuery("from Book2 ").list();
        log.info(list.size());
        Book2 book2 = (Book2) session.get(Book2.class, 2);
        log.info(book2.getName());
    }

    @Test
    public void testOneCacheList2() {
        List<Book2> list = session.createQuery("select name from Book2 ").list();
        log.info(list.size());
        Object bookName = session.createQuery("select name from Book2 where id = :id").setInteger("id", 2).uniqueResult();
        log.info(bookName);
    }

    /**
     * uniqueResult查询不会查缓存,但会放进缓存
     */
    @Test
    public void testOneCacheUnique1() {
        Book2 book2 = (Book2) session.createQuery("from Book2 where id = :id").setInteger("id", 2).uniqueResult();
        log.info(book2.getName());
        Book2 book3 = (Book2) session.createQuery("from Book2 where id = :id").setInteger("id", 2).uniqueResult();
        log.info(book3.getName());
    }

    @Test
    public void testOneCacheUnique2() {
        Book2 book2 = (Book2) session.createQuery("from Book2 where id = :id").setInteger("id", 2).uniqueResult();
        log.info(book2.getName());
        book2 = (Book2) session.get(Book2.class, 2);
        log.info(book2.getName());
    }

    /**
     * Iterate会执行查询id的操作,使用缓存,也会放进缓存
     */
    @Test
    public void testOneCacheIterate1() {
        List<Book2> list = session.createQuery("from Book2 ").list();
        log.info(list.size());
        Iterator<Book2> it = session.createQuery("from Book2 ").iterate();
        while (it.hasNext()) {
            Book2 next = it.next();
            log.info(next.getName());
        }
    }

    @Test
    public void testOneCacheIterate2() {
        Iterator<Book2> it = session.createQuery("from Book2 ").iterate();
        while (it.hasNext()) {
            Book2 next = it.next();
            log.info(next.getName());
        }
        it = session.createQuery("from Book2 ").iterate();
        while (it.hasNext()) {
            Book2 next = it.next();
            log.info(next.getName());
        }
    }

    @Test
    public void testOneCacheEvict() {
        Book2 book2 = (Book2) session.get(Book2.class, 1);
        log.info(book2.getName());
        // session.evict(book2);
        session.clear();
        book2 = (Book2) session.get(Book2.class, 1);
        log.info(book2.getName());
    }


}
