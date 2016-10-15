package com.yugi.xml.pojo;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/27.
 */
@Log4j2
public class TestPerson {

    @Test
    public void testOneToOneForeignSave() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Person person1 = new Person();
        Person person2 = new Person();
        IdCard idCard1 = new IdCard();
        IdCard idCard2 = new IdCard();
        idCard1.setCode("001");
        idCard2.setCode("002");
        person1.setName("龟爷");
        person1.setAge(20);
        person1.setIdCard(idCard1);
        person2.setName("虎佛");
        person2.setAge(22);
        person2.setIdCard(idCard2);
  /*      Person person3 = new Person();
        person3.setName("飒爽");
        person3.setAge(24);
        person3.setIdCard(idCard2);
        session.saveOrUpdate(person3);*/
        session.saveOrUpdate(person1);
        session.saveOrUpdate(person2);
        tx.commit();
    }

    @Test
    public void testOneToForeignGet() throws Exception {
        Session session = HibernateUtil.getSession();
        Person person = (Person) session.get(Person.class,1);
        System.out.println(person);
        System.out.println(person.getIdCard());
        System.out.println("===============================");
        IdCard idCard = (IdCard) session.get(IdCard.class, 2);
        System.out.println(idCard);
        System.out.println(idCard.getPerson());
    }

    @Test
    public void testOneToOnePrimarySave() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Person person1 = new Person();
        Person person2 = new Person();
        IdCard idCard1 = new IdCard();
        IdCard idCard2 = new IdCard();
        idCard1.setCode("0001");
        idCard2.setCode("0002");
        person1.setName("龟爷");
        person1.setAge(20);
        person1.setIdCard(idCard1);
        person2.setName("虎佛");
        person2.setAge(22);
        person2.setIdCard(idCard2);
        session.saveOrUpdate(person1);
        session.saveOrUpdate(person2);
        tx.commit();
    }


    @Test
    public void testLog(){
        log.info("test");
    }

}
