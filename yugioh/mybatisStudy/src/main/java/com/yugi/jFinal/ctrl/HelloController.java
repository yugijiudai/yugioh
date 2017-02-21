package com.yugi.jFinal.ctrl;

import com.google.gson.Gson;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.yugi.entity.Dept;
import com.yugi.jFinal.model.BeanModel;
import com.yugi.jFinal.model.DeptModel;
import com.yugi.jFinal.model.EmpModel;

import java.util.List;

/**
 * Created by Administrator on 2017/1/26.
 */
public class HelloController extends Controller {

    public void index() {
        // this.redirect("https://www.baidu.com");
        this.render("/WEB-INF/index.jsp");
        // renderText("Hello JFinal World.");
    }

    // @Before(Tx.class)
    @ActionKey("/test")
    public void sayHello() {
        try {
            List<BeanModel> beanModels = DeptModel.deptModel.find("select dept.* from dept join employ e on dept.empId = e.id ", new Object[]{});
            List<Dept> thisBean = BeanModel.getThisBeanList(beanModels, Dept.class);
            String s = new Gson().toJson(thisBean);
            this.renderJson(s);
            EmpModel empModel = new EmpModel();
            empModel.save2();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
