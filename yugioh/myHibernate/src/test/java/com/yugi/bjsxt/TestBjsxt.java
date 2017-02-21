package com.yugi.bjsxt;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
@Log4j2
public class TestBjsxt {

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
    public void testSave() {
        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setName("c" + i);
            session.save(category);
        }
        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setId(1);
            Topic topic = new Topic("t" + i, new Date());
            topic.setCategory(category);
            session.save(topic);
        }
        for (int i = 0; i < 10; i++) {
            Topic topic = new Topic();
            topic.setId(1);
            Msg msg = new Msg();
            msg.setCont("m" + i);
            msg.setTopic(topic);
            session.save(msg);
        }

        tx.commit();
    }

    @Test
    public void testQuery1() {
        String sql = "from Category";
        List<Category> list = session.createQuery(sql).list();
        for (Category category : list) {
            log.info(category.getName());
        }
    }

    @Test
    public void testQuery2() {
        String sql = "from Topic t where t.msgs is empty ";
        List<Topic> list = session.createQuery(sql).list();
        for (Topic topic : list) {
            log.info("{}-{}", topic.getId(), topic.getTitle());
        }
    }

    @Test
    public void testNamedQuery() {
        Topic topic = (Topic) session.getNamedQuery("topic.selectCertainTopic").setParameter("id", 1).uniqueResult();
        log.info(topic.getTitle());
        topic = (Topic) session.getNamedQuery("topic.ss").setParameter("id", 1).uniqueResult();
        log.info(topic.getTitle());
    }

    @Test
    public void testNativeQuery() {
        String sql = "select * from t_category";
        List<Category> list = session.createSQLQuery(sql).addEntity(Category.class).list();
        for (Category category : list) {
            log.info(category.getName());
        }
    }

    @Test
    public void testQBC() {
        // criterion 标准/准则/约束
        Criteria criteria = session.createCriteria(Topic.class);
        criteria.add(Restrictions.gt("id", 2));
        criteria.add(Restrictions.lt("id", 6));
        // criteria.add(Restrictions.or(Restrictions.lt("id", 2), Restrictions.gt("id", 6)));
        // 相当于join category和criteria.setFetchMode("category", FetchMode.EAGER);或者FetchMode.JOIN
        criteria.createCriteria("category");
        // 相当于 where category.id between 1 and 3(注:如果这里用id而不写category.id,他会变成topic.id), 或者将这个add和createCriteria合并在一行,则不会变成topic.id
        criteria.add(Restrictions.between("category.id", 1, 3));

        List<Topic> list = criteria.list();
        for (Topic topic : list) {
            log.info("id:{}---title{}", topic.getId(), topic.getTitle());
            log.info("{}", topic.getCategory());
        }
    }

    /**
     * QBE不能用主键来查询!!!!
     */
    @Test
    public void testQBE() {
        // DetachedCriteria
        Topic topicExample = new Topic();
        topicExample.setTitle("T_");
        Example example = Example.create(topicExample).ignoreCase().enableLike();
        Criteria criteria = session.createCriteria(Topic.class);
        criteria.add(Restrictions.gt("id", 2));
        criteria.add(Restrictions.lt("id", 6));
        criteria.add(example);
        List<Topic> list = criteria.list();
        for (Topic topic : list) {
            log.info("id:{}---title:{}", topic.getId(), topic.getTitle());
        }
    }


    @Test
    public void tes() {
        String hql = "from Xa x where x.caId in (select c.id from Category c)";
        // String hql = "select x from Xa x, Category c where x.caId=c.id";
        List<Xa> list = session.createQuery(hql).list();


        for (Xa xa : list) {
            System.out.println(xa);
        }
    }

}
