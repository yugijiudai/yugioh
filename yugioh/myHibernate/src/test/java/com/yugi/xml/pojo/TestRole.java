package com.yugi.xml.pojo;

import com.yugi.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Administrator on 2016/9/27.
 */
@Log4j2
public class TestRole {

    @Test
    public void testManyToManySave() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Function function1 = new Function("用户管理", "user_mag", "userAction");
        Function function2 = new Function("角色管理", "role_mag", "roleAction");
        Function function3 = new Function("系统管理", "sys_mag", "sysAction");
        Function function4 = new Function("权限管理", "prev_mag", "prevAction");
        Role role1 = new Role();
        role1.setName("admin");
        role1.getFunctions().add(function1);
        role1.getFunctions().add(function2);
        role1.getFunctions().add(function3);
        role1.getFunctions().add(function4);
        Role role2 = new Role();
        role2.setName("vip");
        role2.getFunctions().add(function1);
        role2.getFunctions().add(function2);
        session.saveOrUpdate(role1);
        session.saveOrUpdate(role2);
        tx.commit();
    }

    @Test
    public void testManyToManyGet() throws Exception {
        Session session = HibernateUtil.getSession();
        Role role = (Role) session.get(Role.class, 1);
        log.info(role.getName());
        Set<Function> functions = role.getFunctions();
        for (Function function : functions) {
            log.info(function);
        }
        log.warn("=================================");
        Function function = (Function) session.get(Function.class, 3);
        log.info(function.getName());
        Set<Role> roles = function.getRoles();
        for (Role role1 : roles) {
            log.info(role1);
        }
    }


    @Test
    public void testManyToManyDelete() throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Role role = (Role) session.get(Role.class, 2);
        session.delete(role);
        tx.commit();
    }

}
