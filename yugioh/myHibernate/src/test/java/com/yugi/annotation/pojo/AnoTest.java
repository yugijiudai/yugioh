package com.yugi.annotation.pojo;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.LockOptions;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/**
 * @author yugi
 * @apiNote
 * @since 2017-02-23
 */
@Log4j2
public class AnoTest {

    private static int success = 0;
    private static int fail = 0;

    private Session session;

    private Transaction tx;

    // @BeforeClass
    public void setUp() throws Exception {
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
    }

    // @AfterClass
    public void setDown() throws Exception {
        HibernateUtil.closeSession();
    }

    @AfterClass
    public void setD() throws Exception {
        // log.warn("============");
        org.junit.Assert.assertEquals(1, success);
        org.junit.Assert.assertEquals(5, fail);
    }


    @Test(invocationCount = 10, threadPoolSize = 10)
    public void testPessimisticLock5() throws Exception {
        Session session2 = HibernateUtil.getSession();
        Transaction tx1 = session2.beginTransaction();
        User user = (User) session2.get(User.class, 1, LockOptions.UPGRADE);
        log.info(user.getName());
        Thread.sleep(1000);
        tx1.commit();
    }

    @Test(invocationCount = 6, threadPoolSize = 6)
    // @Test(invocationCount = 3, threadPoolSize = 3, expectedExceptions = {HibernateException.class}, expectedExceptionsMessageRegExp = "乐观锁更新.*")
    public void testPessimisticLock2() {
        Session session1 = HibernateUtil.factory.openSession();
        Transaction tx1 = session1.beginTransaction();
        int i = 0;
        User user1 = (User) session1.get(User.class, 1);
        String updateSql = "update t_user set name = 'dwe',version = version + 1 where id = 1 and version = ? ";
        SQLQuery sqlQuery2 = session1.createSQLQuery(updateSql);
        sqlQuery2.setParameter(0, user1.getVersion());
        i = sqlQuery2.executeUpdate();
        tx1.commit();
        if (i == 0) {
            fail++;
            // log.fatal("乐观锁更新失败!");
            // throw new HibernateException("乐观锁更新失败!");
        }
        else {
            success++;
            // log.fatal("乐观锁更新成功!");
            // throw new HibernateException("乐观锁更新成功!");
        }

    }


}
