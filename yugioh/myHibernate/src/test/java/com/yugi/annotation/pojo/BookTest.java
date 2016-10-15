package com.yugi.annotation.pojo;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
@Log4j2
public class BookTest {

    private Session session;

    private Transaction tx;


    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
    }

    @Test
    public void testSaveBook() throws Exception {
        Book book = new Book("电击萌王", 80.0, "咳咳", new Date());
        Book book2 = new Book("anime", 80.0, "咳咳", new Date());
        Book book3 = new Book("bilibili", 80.0, "咳咳", new Date());
        Book book4 = new Book("大学", 80.0, "咳咳", new Date());
        Book book5 = new Book("我勒个去", 80.0, "咳咳", new Date());
        Category category1 = new Category("文学");
        Category category2 = new Category("历史");
        Category category3 = new Category("哲学");
        Category category4 = new Category("科幻");
        Category category5 = new Category("恐怖");
        book.setCategory(category1);
        book2.setCategory(category2);
        book3.setCategory(category3);
        book4.setCategory(category4);
        book5.setCategory(category5);
        session.save(book);
        session.save(book2);
        session.save(book3);
        session.save(book4);
        session.save(book5);
        tx.commit();
    }


    @Test
    public void testLoadBook() {
        Book book = (Book) session.get(Book.class, 1);
        log.info(book);
        log.info(book.getCategory());
    }

    /**
     * 查询单个属性
     */
    @Test
    public void testQuery1() {
        // 查询所有书名
        // 创建query对象
        // Book 大写表示的是对应的pojo类
        // name 表示的是Book类中的属性名
        String hql = "select name from Book ";
        Query query = session.createQuery(hql);
        // list()方法返回查询结果,返回结果的类型是根据查询的列决定的
        List<String> list = query.list();
        for (String bookName : list) {
            log.info(bookName);
        }
    }

    /**
     * 查询多个属性
     */
    @Test
    public void testQuery2() {
        // 查询所有书的名称和价格
        String hql = "select name,price from Book ";
        // 查询多个列时,返回结果是数组集合,数组中元素的类型是由查询列来决定
        List<Object[]> list = session.createQuery(hql).list();
        for (Object[] objects : list) {
            log.info("{}:{}", objects[0], objects[1]);
        }
    }

    /**
     * 查询多个列时,将查询结果封装为对象集合
     */
    @Test
    public void testQuery3() {
        String hql = "select new Book(name,price) from Book ";
        List<Book> list = session.createQuery(hql).list();
        for (Book books : list) {
            log.info("{}:{}", books.getName(), books.getPrice());
        }
    }

    /**
     * 别名的使用
     */
    @Test
    public void testQuery4() {
        String hql = "select new Book(b.name,b.price) from Book b";
        List<Book> list = session.createQuery(hql).list();
        for (Book books : list) {
            log.info("{}:{}", books.getName(), books.getPrice());
        }
    }

    /**
     * 查询所有列
     */
    @Test
    public void testQuery5() {
        String hql = "from Book ";
        List<Book> list = session.createQuery(hql).list();
        for (Book books : list) {
            log.info(books);
        }
    }

    /**
     * 查询所有列2,不能使用*,需要使用别名
     */
    @Test
    public void testQuery6() {
        String hql = "select b from Book b";
        List<Book> list = session.createQuery(hql).list();
        for (Book books : list) {
            log.info(books);
        }
    }

    /**
     * 条件查询 占位符从0开始
     */
    @Test
    public void testQuery7() {
        String hql = " from Book b where id < ? ";
        List<Book> list = session.createQuery(hql).setInteger(0, 3).list();
        for (Book books : list) {
            log.info(books);
        }
    }

    /**
     * 条件查询 setParameter不用理会参数类型
     */
    @Test
    public void testQuery8() {
        String hql = " from Book b where id < ? ";
        List<Book> list = session.createQuery(hql).setParameter(0, 3).list();
        for (Book books : list) {
            log.info(books);
        }
    }

    /**
     * 条件查询 命名查询--这是条件参数的名称,以冒号开头后跟名称,设置参数时只需指定名
     */
    @Test
    public void testQuery9() {
        String hql = " from Book b where id < :id ";
        List<Book> list = session.createQuery(hql).setParameter("id", 3).list();
        for (Book books : list) {
            log.info(books);
        }
    }


    /**
     * 分页查询
     */
    @Test
    public void testQuery10() {
        String hql = " from Book b ";
        List<Book> list = session.createQuery(hql).setFirstResult(0).setMaxResults(3).list();
        for (Book books : list) {
            log.info(books);
        }
    }

    /**
     * 聚合函数--统计查询,结果唯一
     */
    @Test
    public void testQuery11() {
        //查询图书总数
        String hql = "select count(b.name) from Book b ";
        //可以是int,long
        Number count = (Number) session.createQuery(hql).uniqueResult();
        log.info("总数:{}", count.intValue());
    }

    /**
     * 分组查询
     */
    @Test
    public void testQuery12() {
        String hql = "select b.category.name,count(b.id) from Book b group by b.category.name";
        List<Object[]> list = session.createQuery(hql).list();
        for (Object[] objects : list) {
            log.info("{}:{}", objects[0], objects[1]);
        }
    }


    /**
     * 排序
     */
    @Test
    public void testQuery13() {
        String hql = "from Book b order by b.price desc ";
        List<Book> list = session.createQuery(hql).list();
        for (Book books : list) {
            log.info(books);
        }
    }

    /**
     * 对象导航--连接查询
     */
    @Test
    public void testQuery14() {
        //查询哲学类的书籍信息
        // String hql = "from Book b where b.category.name like :name ";
        String hql = "select b from Book b join b.category c where c.name like :name ";
        List<Book> list = session.createQuery(hql).setString("name", "%哲%").list();
        for (Book books : list) {
            log.info(books);
        }
    }


    /**
     * 左外连接
     */
    @Test
    public void testQuery15() {
        //查询哲学类的书籍信息
        String hql = "select c.name,b.name from Category c left outer join c.books b ";
        List<Object[]> list = session.createQuery(hql).list();
        for (Object[] objects : list) {
            log.info("{}:{}", objects[0], objects[1]);
        }
    }


    /**
     * 过滤器的使用--过滤查询,为查询加上某些条件
     * 1.定义过滤器
     * 2.使用:加条件
     * 3.在查询时候使得过滤器生效
     */
    @Test
    public void testQuery16() {
        String hql = "from Book b";
        //启用过滤器
        session.enableFilter("bf").setParameter("id",3);
        // session.enableFilter("bookFilter").setParameter("id",3);
        List<Book> list = session.createQuery(hql).list();
        for (Book books : list) {
            log.info(books);
        }
    }


}
