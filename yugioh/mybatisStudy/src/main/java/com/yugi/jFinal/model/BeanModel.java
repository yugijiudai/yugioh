package com.yugi.jFinal.model;

import com.google.gson.Gson;
import com.jfinal.plugin.activerecord.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/26.
 */
public class BeanModel extends Model<BeanModel> {


    public static List getThisBeanList(List<? extends Model> models, Class T) throws Exception {
        List list = new LinkedList<>();
        for (Model model : models) {
            Object obj = new Gson().fromJson(model.toJson(), T);
            list.add(obj);
        }
        return list;
    }

    public static Object getThisBean(Model model, Class T) throws Exception {
        return new Gson().fromJson(model.toJson(), T);
    }

}
