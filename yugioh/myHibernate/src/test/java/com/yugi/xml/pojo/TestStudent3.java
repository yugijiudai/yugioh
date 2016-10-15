package com.yugi.xml.pojo;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Administrator on 2016/9/28.
 */
@Log4j2
public class TestStudent3 {


    @Test
    public void testOneToManySave() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Student3 student = new Student3("瑠璃", 17);
        Student3 student2 = new Student3("塞妹", 17);
        Student3 student3 = new Student3("凛", 17);
        Grade3 grade = new Grade3("基础班");
        // student.setGrade(grade);
        // student2.setGrade(grade);
        Map<String, Student3> maps = grade.getStudents();
        maps.put(student.getName(),student);
        maps.put(student2.getName(),student2);
        maps.put(student3.getName(),student3);
        session.save(grade);
        session.save(student);
        session.save(student2);
        session.save(student3);
        tx.commit();
    }

    @Test
    public void testGet(){
        Session session = HibernateUtil.getSession();
        Grade3 grade = (Grade3) session.get(Grade3.class,2);
        log.info(grade);
        Map<String, Student3> map = grade.getStudents();
        log.info(map.get("瑠璃"));
        log.warn(map);

    }
}
