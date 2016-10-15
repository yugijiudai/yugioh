package com.yugi.xml.pojo;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
@Log4j2
public class TestStudent2 {

    @Test
    public void testOneToManySave() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Student2 student = new Student2("瑠璃", 17);
        Student2 student2 = new Student2("塞妹", 17);
        Student2 student3 = new Student2("凛", 17);
        Grade2 grade = new Grade2("基础班");
        // student.setGrade(grade);
        // student2.setGrade(grade);
        grade.getStudents().add(student);
        grade.getStudents().add(student2);
        grade.getStudents().add(student3);
        //保存数据的顺序是根据外键的配置来决定的,如果not-null="true",那么先保存一的一端,否则可以随意保存
        session.save(grade);
        // session.save(student);
        // session.save(student2);
        // session.save(student3);
        tx.commit();
    }

    @Test
    public void testGet(){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Grade2 grade2 = (Grade2) session.get(Grade2.class,1);
        List<Student2> students = grade2.getStudents();
        log.info(students.get(1).getName());
        // for (Student2 student : students) {
        //     log.info(student);
        // }
        students.remove(1);
        session.saveOrUpdate(grade2);
        tx.commit();

    }


}
