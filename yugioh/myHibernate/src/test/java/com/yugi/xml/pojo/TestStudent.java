package com.yugi.xml.pojo;

import com.yugi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import java.sql.Blob;
import java.sql.Clob;

/**
 * Created by Administrator on 2016/9/18.
 */
public class TestStudent {

    @Test
    public void testOneToManySave() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Student student = new Student();
        student.setName("瑠璃");
        student.setAge(17);
        Blob serialBlob = new SerialBlob("ttt".getBytes());
        Clob clob = new SerialClob("sss".toCharArray());
        student.setImage(serialBlob);
        student.setIntroduce(clob);

        Student student2 = new Student();
        student2.setName("塞妹");
        student2.setAge(17);
        Blob serialBlob2 = new SerialBlob("ttt".getBytes());
        Clob clob2 = new SerialClob("sss".toCharArray());
        student2.setImage(serialBlob2);
        student2.setIntroduce(clob2);

        //关联
        Grade grade = new Grade();
        grade.setName("基础班");
        student.setGrade(grade);
        student2.setGrade(grade);
        // grade.getStudents().add(student);
        // grade.getStudents().add(student2);
        //保存数据的顺序是根据外键的配置来决定的,如果not-null="true",那么先保存一的一端,否则可以随意保存
        // session.save(grade);
        session.save(student);
        session.save(student2);
        tx.commit();

        /*grade级联saveOrUpdate的情况下可以直接
        grade.getStudents().add(student);
        grade.getStudents().add(student2);
        session.save(grade);*/

        /*student级联saveOrUpdate的情况下可以直接
        student.setGrade(grade);
        student2.setGrade(grade);
        session.save(student);
        session.save(student2);*/
    }

    @Test
    public void testInverse() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Student student = new Student();
        student.setName("瑠璃");
        student.setAge(17);
        Student student2 = new Student();
        student2.setName("塞妹");
        student2.setAge(17);

        Grade grade = new Grade();
        grade.setName("基础班");
        // grade.getStudents().add(student);
        // grade.getStudents().add(student2);
        student.setGrade(grade);
        student2.setGrade(grade);
        session.save(grade);
        session.save(student);
        session.save(student2);
        tx.commit();
    }


    @Test
    public void testManyToOneSave() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Student student = new Student();
        student.setName("瑠璃");
        student.setAge(17);
        Blob serialBlob = new SerialBlob("ttt".getBytes());
        Clob clob = new SerialClob("sss".toCharArray());
        student.setImage(serialBlob);
        student.setIntroduce(clob);

        Grade grade = new Grade();
        grade.setName("基础班");
        student.setGrade(grade);
        session.save(grade);
        // session.save(student);
        tx.commit();
    }

    @Test
    public void testLoadManyToOne() throws Exception {
        Session session = HibernateUtil.getSession();
        Student student = (Student) session.get(Student.class, 2);
        System.out.println(student);
        System.out.println(student.getGrade());
    }

    @Test
    public void testLoadOneToMany() throws Exception {
        Session session = HibernateUtil.getSession();
        Grade grade = (Grade) session.get(Grade.class, 1);
        System.out.println(grade.getName());
        System.out.println(grade.getStudents());
    }

    @Test
    public void testDelete() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Grade grade = (Grade) session.get(Grade.class, 1);
        session.delete(grade);
        tx.commit();
    }


}
