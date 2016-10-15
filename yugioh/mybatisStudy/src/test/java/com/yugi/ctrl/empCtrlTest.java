package com.yugi.ctrl;

import com.google.gson.Gson;
import com.yugi.entity.Emp;
import com.yugi.util.MockUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Administrator on 2016/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:/spring.xml", "classpath*:/spring-web.xml"})
public class empCtrlTest {

    @Resource
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void ajax() throws Exception {
        // MvcResult mvcResult = mockMvc.perform((post("/third/third.do").param("id", "111").param("name", "龟爷")))
        MvcResult mvcResult = mockMvc.perform((post("/third/ajax.do").param("id", "111").param("name", "龟爷")))
                // .andExpect(status().isOk())
                // .andExpect(content().string("{'foo':'bar'}"))
                // .andExpect(view().name("emp/third"))
                // .andExpect(model().attributeExists("emp"))
                .andDo(print())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(result);
    }


    @Test
    public void testPostJson() throws Exception {

        Emp emp = new Emp();
        emp.setName("龟爷");
        emp.setId(12L);
        String json = new Gson().toJson(emp);
        String result = MockUtil.mvcPostJson(mockMvc, "/third/hehe.do", json);
        System.out.println(result);
    }

    @Test
    public void testPostPara() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", "111");
        map.put("name", "虎佛");
        // String result = MockUtil.mvcPostPara(mockMvc, "/third/third.do", map, "emp/third", new String[]{"emp","id"}, null, true);
        String result = MockUtil.mvcPostPara(mockMvc, "/third/ajax.do", map);
        System.out.println(result);
    }

}