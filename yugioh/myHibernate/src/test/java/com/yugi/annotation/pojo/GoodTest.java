package com.yugi.annotation.pojo;

import com.yugi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/11.
 */
public class GoodTest {

    private Session session;

    private Transaction tx;


    @Before
    public void setUp() throws Exception {
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
    }


    private Good getGood(Integer id) {
        return (Good) session.get(Good.class, id);
    }

    private List<BindGood> getBindGoods() {
        List<BindGood> bindGoods = new ArrayList<>();
        BindGood bindGood = new BindGood();
        BindGood bindGood2 = new BindGood();
        Good good = this.getGood(1);
        bindGood.setGood(good);
        Good good2 = this.getGood(2);
        bindGood2.setGood(good2);
        bindGoods.add(bindGood);
        bindGoods.add(bindGood2);
        return bindGoods;
    }

    private List<BindGood> getBindGoods2() {
        List<BindGood> bindGoods = new ArrayList<>();
        BindGood bindGood = new BindGood();
        BindGood bindGood2 = new BindGood();
        Good good = new Good();
        good.setId(1);
        bindGood.setGood(good);
        Good good2 = new Good();
        good2.setId(2);
        bindGood2.setGood(good2);
        bindGoods.add(bindGood);
        bindGoods.add(bindGood2);
        return bindGoods;
    }

    private List<BindGood> getUpdateBindGoods() {
        List<BindGood> bindGoods = new ArrayList<>();
        BindGood bindGood = new BindGood();
        bindGood.setId(1);
        Good good = new Good();
        good.setId(4);
        bindGood.setGood(good);
        BindGood bindGood2 = new BindGood();
        bindGood2.setId(2);
        Good good2 = new Good();
        good2.setId(4);
        bindGood2.setGood(good2);


        bindGoods.add(bindGood);
        // bindGoods.add(bindGood2);
        return bindGoods;
    }

    @Test
    public void init() {
        HibernateUtil.CreateDB();
        Good good = new Good();
        good.setGoodName("物品一");
        session.save(good);
        Good good2 = new Good();
        good2.setGoodName("物品二");
        session.save(good2);
        Good good3 = new Good();
        good3.setGoodName("物品三");
        session.save(good3);
        tx.commit();
    }

    @Test
    public void save() {
        GoodTemplate goodTemplate = new GoodTemplate();
        goodTemplate.setTemplateName("模板3");
        List<BindGood> bindGoods = this.getBindGoods2();
        goodTemplate.setBindGoods(bindGoods);
        session.saveOrUpdate(goodTemplate);
        tx.commit();
    }

    @Test
    public void update() {
        GoodTemplate goodTemplate = (GoodTemplate) session.get(GoodTemplate.class, 1);
        List<BindGood> updateBindGoods = this.getUpdateBindGoods();
        goodTemplate.setBindGoods(updateBindGoods);
        session.saveOrUpdate(goodTemplate);
        tx.commit();
        // this.assertQuery();
    }

    @Test
    public void delete() {
        Good goodTemplate = (Good) session.get(Good.class, 1);
        session.delete(goodTemplate);
        tx.commit();
    }


    private void assertQuery() {
        GoodTemplate goodTemplate = (GoodTemplate) session.get(GoodTemplate.class, 1);
        Assert.notNull(goodTemplate);
        List<BindGood> bindGoods = goodTemplate.getBindGoods();
        Assert.notEmpty(bindGoods);
        BindGood bindGood = bindGoods.get(0);
        Assert.notNull(bindGood);
        Good good = bindGood.getGood();
        Assert.notNull(good);
        org.junit.Assert.assertEquals(3L, good.getId().longValue());
    }


}
