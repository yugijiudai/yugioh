package com.yugi.ctrl;

import com.google.gson.Gson;
import com.yugi.dao.EmpMapper;
import com.yugi.entity.Emp;
import com.yugi.service.EmpSrv;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */
@Controller
@RequestMapping("/third")
public class empCtrl {

    @Resource
    private EmpSrv empSrv;

    @Resource
    private EmpMapper empMapper;


    @RequestMapping("third.do")
    public String third(ModelMap map) {
        List<Emp> emps = empSrv.queryForAll();
        for (Emp emp : emps) {
            System.out.println(emp);
        }
        System.out.println("---------------");
        emps = empMapper.queryForEmp();
        map.put("emp", emps);
        map.put("id", 123);
        System.out.println(emps);
        return "emp/third";
    }

    @ResponseBody
    @RequestMapping(value = "ajax")
    public String ajax(Integer id, String name, HttpServletResponse response) {
        System.out.println(id);
        System.out.println(name);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return "{'foo':'bar'}";
    }

    @ResponseBody
    // @RequestMapping(value = "hehe", produces = {"text/html;charset=UTF-8;", "application/json;"})
    @RequestMapping(value = "hehe")
    public String hehe(@RequestBody Emp emp) {
        System.out.println(emp);
        // response.setCharacterEncoding("UTF-8");
        return new Gson().toJson(emp);
    }

}
