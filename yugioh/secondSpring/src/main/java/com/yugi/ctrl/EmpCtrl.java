package com.yugi.ctrl;

import com.yugi.annotation.CheckToken;
import com.yugi.annotation.Token;
import com.yugi.constants.SpringConstant;
import com.yugi.dao.EmpDao;
import com.yugi.impl.BaseMe;
import com.yugi.logger.LogType;
import com.yugi.pojo.Emp;
import com.yugi.pojo.ListEmp;
import com.yugi.service.RedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmpCtrl {
    final Logger errorLog = LogManager.getLogger(LogType.ERROR);
    final Logger sqlLog = LogManager.getLogger(LogType.SQL);
    final Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private RedisService redisServiceImpl;

    @Resource
    private EmpDao empDaoImpl;


    @Resource
    private BaseMe baseMe;

    private static int count = 0;


    @RequestMapping("redis.do")
    @ResponseBody
    public Emp redis() {
        return redisServiceImpl.get("com.yugi.impl.RedisServiceImplTest.del--id:1", Emp.class);
    }

    @Token
    @RequestMapping("login.do")
    public String login(HttpServletRequest request) throws IOException {
        return "emp/login";
    }

    @Token
    @RequestMapping("login2.do")
    public String login2(HttpServletRequest request) {
        String msg = request.getParameter(SpringConstant.REPEATEDMSG);
        request.setAttribute(SpringConstant.REPEATEDMSG, msg);
        return "emp/login";
    }

    @RequestMapping("mo.do")
    public String mo() {
        return "emp/mo";
    }

    @RequestMapping("son.do")
    public String son() {
        return "emp/son";
    }


    @CheckToken
    @RequestMapping("register.do")
    public String register(HttpServletRequest request, RedirectAttributes ra) throws InterruptedException {
        logger.warn("进入");
        count++;
        Object attribute = request.getAttribute(SpringConstant.REPEATEDMSG);
        if (attribute != null) {
            logger.warn("重复");
            ra.addAttribute(SpringConstant.REPEATEDMSG, attribute);
            return "redirect:/emp/login2.do";
        }
        if (count == 1) {
            LinkedList<Long> list = new LinkedList();
            for (Long i = 0L; i < 30000000L; i++) {
                list.add(3L);
            }
        }
        // return "emp/login";
        count = 0;
        logger.warn("完成");
        return "redirect:/emp/login.do";
    }


    @RequestMapping("second.do")
    public String getSecond(ModelMap map) {
        map.put("hehe", "呵呵");
//        errorLog.error("error");
//        sqlLog.debug("sql");
//        logger.info("log4j");
//         Emp emp = new Emp();
//         emp.setName("虎佛啊");
//         empDaoImpl.update(emp);
//         baseMe.fi5();
        return "emp/second";
    }


    @RequestMapping("transfer1.do")
    public String transfer1(RedirectAttributes ra) {
        //addAttribute可以通过request.getParameter()获取出来
        ra.addAttribute("a", "aa");
        //addFlashAttribute可以通过ModelMap或者model获取出来,相当于session,
        //不过这个只能使用一次,如果里面保存了对象再重定向就会无效,如果是基本类型可以通过@RequestParam来再次获取
        ra.addFlashAttribute("b", "bb");
        Emp ta = new Emp();
        ta.setId(1);
        ta.setName("name");
        ra.addFlashAttribute("ta", ta);
        return "redirect:/emp/transfer2.do";
    }

    @RequestMapping("transfer2.do")
    public String transfer2(ModelMap model, @ModelAttribute("ta") Emp ta) {
        System.out.println(model);
        System.out.println(ta);
        return "redirect:/emp/transfer3.do";
    }

    @RequestMapping("transfer3.do")
    public String transfer3(ModelMap model, @RequestParam("b") String b) {
        System.out.println(model);
        System.out.println(b);
        return "emp/transfer3";
    }


//    @RequestMapping(value = "/first", method = {RequestMethod.POST})
//    @ResponseBody
//    public String getFirst(ListEmp listEmp, Emp emp){
//        System.out.println(listEmp);
//        System.out.println(emp);
//        return "emp/first";
//    }

    /**
     * 将Emp[]改成都可以接收到List<Emp>
     *
     * @param listEmp
     * @return
     */
    @RequestMapping(value = "/first", method = {RequestMethod.POST})
    @ResponseBody
    public String getFirst(@RequestBody Emp[] listEmp) {
        for (Emp emp : listEmp) {
            System.out.println(emp);
        }
        return "emp/first";
    }


    @RequestMapping(value = "/third", method = {RequestMethod.POST})
    @ResponseBody
    public String getThird(ListEmp listEmp, Emp emp) {
        System.out.println(listEmp);
        System.out.println(emp);
        return "emp/first";
    }


    /**
     * var data3 = {emps:[{"id": "5", "name": "sf"},{"id": "6", "name": "sfe"}]};
     * 可以传到ListEmp中的Emp[]
     *
     * @param listEmp
     * @return
     */
    @RequestMapping(value = "/test", method = {RequestMethod.POST})
    @ResponseBody
    public String getTest(@RequestBody ListEmp listEmp) {
        System.out.println(listEmp);
        return "emp/first";
    }


    /**
     * 绑定器,表单传参可以用emem.name这种方式来传
     *
     * @param binder
     */
    @InitBinder()
    public void initBinder(ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("emem.");
    }

    /**
     * pattern = "yyyy:MM:dd"的意思是:传过来的日期一定要按这个格式才能被正确接收到
     *
     * @param value
     * @return
     */
    @RequestMapping("date.do")
    @ResponseBody
    public String date(@DateTimeFormat(pattern = "yyyy:MM:dd") Date value) {
        System.out.println(value);
        return "Converted date " + value;
    }


}
