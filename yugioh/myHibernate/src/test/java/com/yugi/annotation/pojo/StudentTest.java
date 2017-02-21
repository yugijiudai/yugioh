package com.yugi.annotation.pojo;

import com.yugi.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */
public class StudentTest {

    private Session session;

    private Transaction tx;


    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
    }

    @Test
    public void init() {
        HibernateUtil.CreateDB();
        Teacher teacher = new Teacher();
        teacher.setTName("阿龟");
        session.saveOrUpdate(teacher);
        Student student = new Student();
        student.setSname("虎佛");
        student.setTid(teacher.getTid());
        Student student2 = new Student();
        student2.setSname("飒爽");
        student2.setTid(teacher.getTid());
        Student student3 = new Student();
        student3.setSname("宏飞");
        student3.setTid(teacher.getTid());
        Student student4 = new Student();
        student4.setSname("龟爷");
        student4.setTid(teacher.getTid());
        session.saveOrUpdate(student);
        session.saveOrUpdate(student2);
        session.saveOrUpdate(student3);
        session.saveOrUpdate(student4);
        tx.commit();
    }


    public List<Student> getStudent() {
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setSid(1L);
        student.setSname("豪哥2");
        Student student3 = new Student();
        student3.setSid(2L);
        student3.setSname("纽拉2");
        Student student2 = new Student();
        student2.setSname("浩霆");
        list.add(student);
        list.add(student2);
        list.add(student3);
        return list;
    }

    @Test
    public void save() {
        Long tid = 1L;
        String hql = "select st from Student st where st.tid = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, tid);
        List<Student> lists = query.list();
        List<Student> students = this.getStudent();
        List<Student> addList = new LinkedList<>();
        List<Student> updateList = new LinkedList<>();
        List<Student> deleteList = new LinkedList<>();
        for (Student student : students) {
            if (student.getSid() == null) {
                student.setTid(tid);
                addList.add(student);
                continue;
            }
            updateList.add(student);
        }
        for (Student list : lists) {
            boolean updateFlag = false;
            for (Student student : updateList) {
                if (list.getSid().equals(student.getSid())) {
                    list.setSname(student.getSname());
                    updateFlag = true;
                    break;
                }
            }
            if (!updateFlag) {
                deleteList.add(list);
            }
        }
        for (Student student : addList) {
            session.saveOrUpdate(student);
        }
        for (Student student : deleteList) {
            session.delete(student);
        }
        tx.commit();
    }

}
