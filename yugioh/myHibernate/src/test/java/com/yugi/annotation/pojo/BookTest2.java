package com.yugi.annotation.pojo;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/30.
 */
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class BookTest2 {

    private Session session;

    private Transaction tx;

    @Resource
    private JdbcTemplate jdbcTemplate;


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
    public void testJdbcBatch() {
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                String sql = "insert into t_book (name,price,author,pubDate) values(?,?,?,?)";
                connection.setAutoCommit(false);
                PreparedStatement psmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                for (int i = 0; i < 100000; i++) {
                    psmt.setString(1, "电击萌王" + i);
                    psmt.setDouble(2, 80.0);
                    psmt.setString(3, "咳咳");
                    psmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                    psmt.addBatch();
                }
                psmt.executeBatch();
                connection.commit();
            }
        });
    }

    @Test
    public void testSpringJdbcBatch() throws SQLException {
        String sql = "insert into t_book (name,price,author,pubDate) values(?,?,?,?)";
        List<Object[]> list = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(new Object[]{"电击萌王" + i, 80.0, "咳咳", new Date()});
        }
        jdbcTemplate.batchUpdate(sql, list, 100, new ParameterizedPreparedStatementSetter<Object[]>() {
            @Override
            public void setValues(PreparedStatement ps, Object[] argument) throws SQLException {
                ps.setString(1, (String) argument[0]);
                ps.setDouble(2, Double.parseDouble(argument[1].toString()));
                ps.setString(3, (String) argument[2]);
                ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            }
        });
    }


    @Test
    public void testSaveBatch() throws Exception {
        for (int i = 0; i < 100; i++) {
            Book2 book2 = new Book2("电击萌王" + i, 80.0, "咳咳", new Date());
            Category2 category2 = new Category2("哲学");
            book2.setCategory2(category2);
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
            session.save(book2);
        }
        tx.commit();
    }


    @Test
    public void testSaveBook2() throws Exception {
        Book2 book = new Book2("电击萌王", 80.0, "咳咳", new Date());
        Book2 book2 = new Book2("anime", 80.0, "咳咳", new Date());
        Book2 book3 = new Book2("bilibili", 80.0, "咳咳", new Date());
        Book2 book4 = new Book2("大学", 80.0, "咳咳", new Date());
        Book2 book5 = new Book2("我勒个去", 80.0, "咳咳", new Date());
        Category2 category1 = new Category2("文学");
        Category2 category2 = new Category2("历史");
        Category2 category3 = new Category2("哲学");
        Category2 category4 = new Category2("科幻");
        Category2 category5 = new Category2("恐怖");
        book.setCategory2(category1);
        book2.setCategory2(category1);
        book3.setCategory2(category1);
        book4.setCategory2(category4);
        book5.setCategory2(category5);
        session.save(book);
        session.save(book2);
        session.save(book3);
        session.save(book4);
        session.save(book5);
        tx.commit();
    }

    @Test
    public void testGetBook() {
        Book2 book2 = (Book2) session.get(Book2.class, 1);
        log.info(book2.getId());
    }


    @Test
    public void testLoadBook() {
        Book2 book2 = (Book2) session.load(Book2.class, 2);
        HibernateUtil.closeSession();
        log.info(book2.getName());
    }

    @Test
    public void testGetCategory() {
        Category2 category2 = (Category2) session.get(Category2.class, 1);
        log.info(category2.getName());
        //set和list是默认懒加载,无论用get或者load
        Set<Book2> books2 = category2.getBooks2();
        for (Book2 book2 : books2) {
            log.info(book2.getName());
        }
    }

    @Test
    public void ff() {
        // List<Object[]> category2 = session.createQuery("select c,b from Category2 c join c.books2 b where b.name like '%anime%'").list();
        // for (Object[] objects : category2) {
        //     for (Object object : objects) {
        //         log.info(object);
        //     }
        // }
        List<MyVo> category2 = session.createQuery("select new com.yugi.annotation.pojo.MyVo(c,b) " +
                "from Category2 c join c.books2 b where b.id = :id").setParameter("id", 1).list();
        for (MyVo myVo : category2) {
            System.out.println(myVo.getCategory2().getName());
        }


    }


    @Test
    public void testLoadCategory() {
        Category2 category2 = (Category2) session.load(Category2.class, 1);
        log.info(category2.getName());
        //set是默认懒加载,无论用get或者load
        log.info(category2.getBooks2().size());
    }


    @Test
    public void testFetchJoinBook() {
        Book2 book2 = (Book2) session.load(Book2.class, 1);
        log.info(book2.getName());
        log.info(book2.getCategory2().getName());
    }


    /**
     * subSelect指出现在集合中
     */
    @Test
    public void testFetchSubSelect() {
        List<Category2> list = session.createCriteria(Category2.class).add(Restrictions.in("id", new Integer[]{1, 3, 5})).list();
        log.info(list.size());
        for (Category2 category2 : list) {
            log.info("name:{}-count:{}", category2.getName(), category2.getBooks2().size());
        }
    }

    /**
     * batch-size用于查询出来一个对象集合,然后迭代每一个对象,这时这个对象和其他对象是一对多, 通过设置数量来让数据库一次查询出对应个数的这个多对象,
     * 如果没设置,则每次查询这个多的对象都要向数据库发出查询
     * 例(多那里有5条,这时迭代多的时候,每次get都会向数据库查一次,如果设置了数量,则一次用in的方式向数据库里查出对应数量的数据)
     * 1有2条,而1的每一条对应多的5条,总共要查询10次,如果设置数量为5,则只需查询2次
     */
    @Test
    public void testFetchBatch() {
        List<Category2> list = session.createQuery("from Category2").list();
        for (Category2 category2 : list) {
            log.info("name:{}-size:{}", category2.getName(), category2.getBooks2().size());
        }
    }


    @Test
    public void testIterator() {
        List<Book2> list = session.createQuery("from Book2 ").list();
        for (Book2 book2 : list) {
            log.info(book2.getName());
        }
        session.close();
        log.fatal("======================================");
        session = HibernateUtil.getSession();
        Iterator<Book2> it = session.createQuery("from Book2 ").iterate();
        while (it.hasNext()) {
            Book2 next = it.next();
            log.info(next.getName());
        }
    }

}
