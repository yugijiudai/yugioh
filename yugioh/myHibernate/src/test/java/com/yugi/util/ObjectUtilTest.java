package com.yugi.util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author yugi
 * @apiNote
 * @since 2017-02-27
 */
public class ObjectUtilTest {


    // List<String> oldList = new ArrayList<>();
    //
    // List<String> newList = new ArrayList<>();

    List<Long> oldList = new ArrayList<>();

    List<Long> newList = new ArrayList<>();

    @Before
    public void setUp() {
        // oldList.add("1");
        // oldList.add("2");
        // oldList.add("3");
        //
        // newList.add("2");
        // newList.add("3");
        // newList.add("5");
        // newList.add("6");
        oldList.add(1L);
        oldList.add(2L);
        oldList.add(3L);

        newList.add(2L);
        newList.add(3L);
        newList.add(5L);
        newList.add(6L);
    }

    @Test
    public void testGetLongHandleList() throws Exception {
        Map<String, Object> result = ObjectUtil.getLongHandleList(oldList, newList);
        System.out.println(result);
    }

}