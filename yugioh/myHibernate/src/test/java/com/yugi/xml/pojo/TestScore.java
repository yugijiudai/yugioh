package com.yugi.xml.pojo;

import com.yugi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/18.
 */
public class TestScore {


    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Score score = new Score();
        ScoreId scoreId = new ScoreId();
        scoreId.setSubjectId(6);
        scoreId.setStuId(2);
        score.setResult(89.00);
        score.setScoreId(scoreId);
        session.save(score);
        tx.commit();
    }

    @Test
    public void testGet(){
        Session session = HibernateUtil.getSession();
        // Score score = new Score();
        ScoreId scoreId = new ScoreId();
        scoreId.setSubjectId(6);
        scoreId.setStuId(2);
        // score.setScoreId(scoreId);
        Score score = (Score) session.get(Score.class,scoreId);
        System.out.println(score);
    }

}
