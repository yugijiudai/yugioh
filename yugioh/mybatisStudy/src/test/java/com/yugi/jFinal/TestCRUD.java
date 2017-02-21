package com.yugi.jFinal;

import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.yugi.entity.Dept;
import com.yugi.jFinal.model.BeanModel;
import com.yugi.jFinal.model.DeptModel;
import com.yugi.jFinal.model.EmpModel;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/1/26.
 */
public class TestCRUD {
    C3p0Plugin c3p0Plugin;
    ActiveRecordPlugin arp;

    @Before
    public void setUp() throws Exception {
        PropKit.use("jdbc.properties");
        // 在configPlugin方法中添加：
        c3p0Plugin = new C3p0Plugin(PropKit.get("jdbc.url"), PropKit.get("jdbc.username"), PropKit.get("jdbc.password"));
        c3p0Plugin.start();
        arp = new ActiveRecordPlugin(c3p0Plugin);
        arp.setShowSql(true);
        arp.addMapping("dept", DeptModel.class);
        arp.addMapping("employ", EmpModel.class);
        arp.start();
    }

    @After
    public void setDown(){
        c3p0Plugin.stop();
        arp.stop();
    }

    @Test
    public void test() {
        List<BeanModel> articleList = EmpModel.empModel.find("select * from employ");
        for (BeanModel empModel : articleList) {
            System.out.println(empModel);
        }
    }

    @Test
    public void getTest() throws Exception {
        List<BeanModel> beanModels = DeptModel.deptModel.find("SELECT dept.* FROM dept JOIN employ e ON dept.empId = e.id ", new Object[]{});
        List<Dept> thisBean = BeanModel.getThisBeanList(beanModels, Dept.class);
        for (Dept dept : thisBean) {
            System.out.println(dept.getName());
        }
    }

    @Test
    public void getTest2() throws Exception {
        BeanModel beanModel = DeptModel.deptModel.findFirst("select * from dept where deptno = ?", 1);
        Dept dept = (Dept) BeanModel.getThisBean(beanModel, Dept.class);
        System.out.println(dept.getName());
    }


    @Test
    public void testTx() {
        Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                EmpModel.empModel.findById(1).set("name", "呵呵").update();
                return false;
            }
        });
    }

    @Test
    public void testTx2() throws Exception {
        EmpModel empModel = new EmpModel();
        empModel.save2();
    }

}
