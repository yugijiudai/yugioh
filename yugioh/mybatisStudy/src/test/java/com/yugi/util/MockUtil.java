package com.yugi.util;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StringUtils;

import java.util.Map;


/**
 * Created by 桜の舞 on 2016/10/15.
 */
public class MockUtil {
    /**
     * mock
     *
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String mock(MockMvc mvc, String url, String json) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(url, "json").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON).content(json.getBytes())).andReturn().getResponse().getContentAsString();
    }


    /**
     * 用参数来post,可以自己定义验证的东西
     *
     * @param mvc        {@link MockMvc}
     * @param url        要请求的url
     * @param params     要请求的参数
     * @param viewName   要校验的视图名字
     * @param modelNames 要校验的model名字
     * @param content    要校验的返回类容
     * @param isPrint    是否打印
     * @return
     * @throws Exception
     */
    public static String mvcPostPara(MockMvc mvc, String url, Map<String, String> params, String viewName, String[] modelNames, String content, boolean isPrint) throws Exception {
        return mvcPost(mvc, url, params, null, null, viewName, modelNames, content, isPrint);
    }

    public static String mvcPostPara(MockMvc mvc, String url, Map<String, String> params) throws Exception {
        return mvcPost(mvc, url, params, null, null, null, null, null, false);
    }

    /**
     * 用json来post,可以自己定义验证的东西
     *
     * @param mvc        {@link MockMvc}
     * @param url        要请求的url
     * @param json       要请求的json参数
     * @param viewName   要校验的视图名字
     * @param modelNames 要校验的model名字
     * @param content    要校验的返回类容
     * @param isPrint    是否打印
     * @return
     * @throws Exception
     */
    public static String mvcPostJson(MockMvc mvc, String url, String json, String viewName, String[] modelNames, String content, boolean isPrint) throws Exception {
        return mvcPost(mvc, url, null, json, MediaType.APPLICATION_JSON, viewName, modelNames, content, isPrint);
    }

    public static String mvcPostJson(MockMvc mvc, String url, String json) throws Exception {
        return mvcPost(mvc, url, null, json, MediaType.APPLICATION_JSON, null, null, null, false);
    }

    /**
     * @param mvc         {@link MockMvc}
     * @param url         要请求的url
     * @param params      要请求的参数
     * @param json        要请求的json参数
     * @param contentType 请求类型
     * @param viewName    要校验的视图名字
     * @param modelNames  要校验的model名字
     * @param content     要校验的返回类容
     * @param isPrint     是否打印
     * @return
     * @throws Exception
     */
    private static String mvcPost(MockMvc mvc, String url, Map<String, String> params, String json, MediaType contentType, String viewName, String[] modelNames, String content, boolean isPrint) throws Exception {
        MockHttpServletRequestBuilder paraBuilder;
        if (MediaType.APPLICATION_JSON.equals(contentType)) {
            paraBuilder = MockMvcRequestBuilders.post(url, "json");
            paraBuilder.contentType(MediaType.APPLICATION_JSON);
            paraBuilder.content(json.getBytes());
        }
        else {
            paraBuilder = MockMvcRequestBuilders.post(url);
            for (String key : params.keySet()) {
                paraBuilder.param(key, params.get(key));
            }
        }
        ResultActions ra = mvc.perform(paraBuilder.characterEncoding("UTF-8")).andExpect(MockMvcResultMatchers.status().isOk());
        checkView(ra, viewName);
        checkModel(ra, modelNames);
        checkContent(ra, content);
        isPrint(ra, isPrint);
        return ra.andReturn().getResponse().getContentAsString();
    }

    private static void checkView(ResultActions ra, String viewName) throws Exception {
        if (!StringUtils.isEmpty(viewName)) {
            ra.andExpect(MockMvcResultMatchers.view().name(viewName));
        }
    }

    private static void checkModel(ResultActions ra, String[] modelNames) throws Exception {
        if (!StringUtils.isEmpty(modelNames) && modelNames.length > 0) {
            for (String modelName : modelNames) {
                ra.andExpect(MockMvcResultMatchers.model().attributeExists(modelName));
            }
        }
    }

    private static void isPrint(ResultActions ra, boolean flag) throws Exception {
        if (flag) {
            ra.andDo(MockMvcResultHandlers.print());
        }
    }

    private static void checkContent(ResultActions ra, String content) throws Exception {
        if (!StringUtils.isEmpty(content)) {
            ra.andExpect(MockMvcResultMatchers.content().string(content));
        }
    }


}
