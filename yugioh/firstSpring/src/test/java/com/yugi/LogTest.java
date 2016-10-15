package com.yugi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Created by Administrator on 2016/4/25.
 */
public class LogTest {

    final Logger log4j2 = LogManager.getLogger(this.getClass());
    final Logger log4j2sql = LogManager.getLogger("log.sql");
    final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(this.getClass());


    @Test
    public void testLog(){
        log4j2.info("log4j2");
        log4j2sql.error("sql");
        log4j.info("log4j");
    }

}
