package com.yugi.dao.test;

import com.yugi.entity.Emp;
import com.yugi.service.EmpSrv;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author yugi
 * @apiNote
 * @since 2017-02-17
 */
@RunWith(Parameterized.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestPara2 {

    private Emp emp;

    private TestContextManager testContextManager;

    @Resource
    protected EmpSrv empSrv;


    @Before
    public void setUpContext() throws Exception {
        //this is where the magic happens, we actually do "by hand" what the spring runner would do for us,
        // read the JavaDoc for the class bellow to know exactly what it does, the method names are quite accurate though
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
    }

    public TestPara2(Emp emp) {
        this.emp = emp;
    }

    @Parameterized.Parameters(name = "2")
    public static Collection getdata() {
        Emp emp = new Emp();
        emp.setName("呵呵");
        Emp emp2 = new Emp();
        emp2.setName("呵呵2");
        return Arrays.asList(new Object[][]{{emp}, {emp2}});
    }

    @Test
    public void test() {
        System.out.println(empSrv);
        System.out.println(emp);
    }

}
