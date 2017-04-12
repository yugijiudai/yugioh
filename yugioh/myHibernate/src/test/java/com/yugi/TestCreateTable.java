package com.yugi;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/29.
 */
public class TestCreateTable {

    @Test
    public void testCreateDB() {
        Configuration cfg = new Configuration().configure();
        SchemaExport se = new SchemaExport(cfg);
        // 第一个参数是否生成ddl脚本,第二个参数是否执行到数据库中
        se.create(true, true);
    }

    @Test
    public void testCreateDB2() {
        // SpringBeanManger
       // LocalSessionFactoryBean sfb = (LocalSessionFactoryBean) SpringBeanManger.getApplicationContext().getBean("&sessionFactory");
       // SchemaExport schema = new SchemaExport(sfb.getConfiguration());
       // schema.create(true, true);
   }


}
