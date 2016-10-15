package com.yugi.xml.pojo;

import com.yugi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/22.
 */
public class TestTeacher {

    @Test
    public void testSave() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Teacher teacher = new Teacher();
        teacher.setName("老湿");
        teacher.setSex("男");
        Address address = new Address();
        address.setAddr1("西三旗");
        address.setAddr2("西直门");
        address.setAddr3("南六环");
        teacher.setAddress(address);
        session.save(teacher);
        tx.commit();
    }
}
