package com.practice;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2016/1/22.
 */
public class TestGetLottery {

    private Bonus bonus;

    @Before
    public void set() {
        int a = 6;
        int b = a + 6;
        System.out.println(b);
        bonus = new Bonus();
    }

    @Test
    public void testLottery() {
        bonus.getLottery();
    }

    public Integer[] sort(Integer[] a) {
        int size = a.length;
        int temp;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
        return a;
    }

    @Test
    public void testList() {
        Integer a[] = new Integer[]{6, 2, 8, 3, 9, 4};
        Integer[] sort = sort(a);
        for (int i : sort) {
            System.out.print(i + ",");
        }
//        List<Integer> list = Arrays.asList(a);
//        Collections.sort(list);
//        for (Integer li : list) {
//            System.out.print(li+",");
//        }
    }


}
