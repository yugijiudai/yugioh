package com.yugi.annotation.pojo;

import com.yugi.bjsxt.*;
import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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


    @Test
    public void test1(){
        Category category = new Category();
        category.setName("c");
        session.save(category);
        tx.commit();
    }

    @Test
    public void test2(){
        Category category = this.getCategory(1);
        System.out.println(category.getBooks().size());
        Book book = new Book("电击萌王", 80.0, "咳咳", new Date());
        Book book2 = new Book("anime", 80.0, "咳咳", new Date());
        Set<Book> books = new HashSet<>();
        books.add(book);
        books.add(book2);
        category.setBooks(books);
        session.saveOrUpdate(category);
        tx.commit();
        session.clear();
        category = this.getCategory(1);
        System.out.println(category.getBooks().size());
    }

    private Category getCategory(Integer id){
        return (Category) session.get(Category.class, id);
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
