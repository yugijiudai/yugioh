package com.yugi.pojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/11.
 */
public class TestMe {


    private static List<Integer> list = new ArrayList<>();

    public void parse() throws InterruptedException {
        if (list.size() != 6) {
            System.out.println(list.size());
            Thread.sleep(500L);
            parse();
        }
        else {
            System.out.println("完成:" + list);
        }
    }

    private static void register() {
        list.add(5);
    }

    static {
        new Thread(() -> {
            try {
                for (int i = 0; i < 6; i++) {
                    register();
                    Thread.sleep(500L);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    @Test
    public void test() {
        String url = "baidu";
        String s = Base64.encodeBase64URLSafeString(url.getBytes());
        System.out.println(s);
        byte[] bytes = Base64.decodeBase64(s);
        System.out.println(new String(bytes));
    }

    @Test
    public void testAili() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        // for (String temp : a) {
        //     if ("1".equals(temp)){
        //         a.remove(temp);
        //     }
        // }
        for (int i = 0; i < a.size(); i++) {
            if ("2".equals(a.get(i))){
                a.remove(i);
            }
        }

        // Iterator<String> iterator = a.iterator();
        // while (iterator.hasNext()){
        //     String temp = iterator.next();
        //     if ("2".equals(temp)){
        //         iterator.remove();
        //     }
        // }

        System.out.println(a);
    }


    public static void main(String[] args) throws InterruptedException {
        // St st = new St();
        // st.setName("呵呵");
        // st.testLog();
        TestMe testMe = new TestMe();
        testMe.parse();
        new TestMe().parse();



    }
}
