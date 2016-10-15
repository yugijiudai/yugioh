package com.practice;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2016/1/22.
 */
public class Bonus {

    public void getLottery() {
        List<Map<String, Object>> list = getList();
        int size = list.size();
        int[] counts = new int[size];
        int[] zones = new int[size + 1];
        zones[0] = 0;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            counts[i] = Integer.parseInt(list.get(i).get("count").toString());
            zones[i + 1] = zones[i] + counts[i];
            sum += counts[i];
        }
        for (int zone : zones) {
            System.out.print(zone + ",");
        }
        if(sum == 0){
            return;
        }
//        int result = 126;
        int result = new Random().nextInt(sum);
        System.out.println("结果:" + result);
        int index = 0;
        for (int i = 0; i < zones.length - 1; i++) {
            if (result >= zones[i] && result < zones[i + 1]) {
                index = i;
                break;
            }
        }
        System.out.println(list.get(index));
        String value = list.get(index).get("result").toString();
        String re = value.contains(",") ? getResult(value) : value;
        System.out.println(re);

    }

    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("money", new BigDecimal("1000"));
        map1.put("result", "1");
        map1.put("count", "0");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("money", new BigDecimal("3000"));
        map2.put("result", "2");
        map2.put("count", "1");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("money", new BigDecimal("5000"));
        map3.put("result", "3,5");
        map3.put("count", "1");
        Map<String, Object> map4 = new HashMap<>();
        map4.put("money", new BigDecimal("7000"));
        map4.put("result", "4,6,7");
        map4.put("count", "0");
        list.add(map1);
        list.add(map2);
        list.add(map4);
        list.add(map3);
        return list;
    }


    public String getResult(String value) {
        String[] val = value.split(",");
        int result = new Random().nextInt(val.length);
        return val[result];
    }


}
