package com.yugi.xml.entity;

import com.yugi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/29.
 */
public class TestExtendPerson {

    private Session session;

    private Transaction tx;

    @Before
    public void setValue() {
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
    }

    /**
     * 只有一张表
     *
     * @throws Exception
     */
    @Test
    public void testSaveExtendOne() throws Exception {
        Teacher yugi = new Teacher("DDD", 16, 7000);
        Student student1 = new Student("xyz", 22, "xy加农");
        Student student2 = new Student("青眼", 20, "毁灭的爆裂疾风弹");
        session.save(yugi);
        session.save(student1);
        session.save(student2);
        tx.commit();
    }

    @Test
    public void testLoadExtendOne() {
        Person person = (Person) session.get(Person.class, 2);
        if (person instanceof Student) {
            Student stu = (Student) person;
            System.out.println(stu.getWork());
        }
    }


    /**
     * 每个子类一张表,每张表都有person的内容
     *
     * @throws Exception
     */
    @Test
    public void testSaveExtendSubclass() throws Exception {
        Teacher yugi = new Teacher("DDD", 16, 7000);
        Student student1 = new Student("xyz", 22, "xy加农");
        Student student2 = new Student("青眼", 20, "毁灭的爆裂疾风弹");
        yugi.setId(1);
        student1.setId(2);
        student2.setId(3);
        session.save(yugi);
        session.save(student1);
        session.save(student2);
        tx.commit();
    }

    @Test
    public void testLoadExtendSubclass() {
        this.testLoadExtendOne();
    }


    /**
     * 三张表,每张表只有自己对应的实体类的内容
     *
     * @throws Exception
     */
    @Test
    public void testSaveExtendEveryClass() throws Exception {
        this.testSaveExtendOne();
    }

    @Test
    public void testLoadExtendEveryClass() {
        this.testLoadExtendOne();
    }
}
