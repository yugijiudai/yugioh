package com.yugi.dao.test;

import com.yugi.dao.AccountMapper;
import com.yugi.dao.EmpMapper;
import com.yugi.entity.Emp;
import com.yugi.service.EmpSrv;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class AccountMapperTest {

    @Resource
    private EmpSrv empSrv;

    @Resource
    private EmpMapper empMapper;

//    @Resource
    @Inject
    private AccountMapper accountMapper;


    @Before
    public void setUp() throws Exception {
        System.err.println("====================================");
    }


    @Test
    public void testFindByPage() throws Exception {
//        EmpSrv empSrv = new EmpSrvImpl();
        List<Emp> emps = empSrv.queryForAll();
        for (Emp emp : emps) {
            System.out.println(emp);
        }
        System.err.println("---------------------");
        emps = empMapper.queryForEmp();
        System.out.println(emps);
    }


    @Test
    public void testQueryEmp() throws Exception {
        List<Map<String, Object>> maps = accountMapper.queryEmp();
        System.out.println(maps);
    }



}