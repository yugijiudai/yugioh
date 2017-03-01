package com.yugi.util;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yugi
 * @apiNote
 * @since 2017-02-27
 */
public class ObjectUtil {

    public static final String ADDLIST = "addList";

    public static final String UPDATELIST = "updateList";

    public static final String DELETELIST = "deleteList";


    public static Map<String, Object> getLongHandleList(List<Long> oldList, List<Long> newList) {
        List<String> oldLists = new LinkedList<>();
        List<String> newLists = new LinkedList<>();
        for (Long aLong : oldList) {
            oldLists.add(aLong.toString());
        }
        for (Long aLong : newList) {
            newLists.add(aLong.toString());
        }
        return getStringHandleList(oldLists, newLists);
    }

    public static Map<String, Object> getStringHandleList(List<String> oldList, List<String> newList) {
        Collection<String> deleteList = CollectionUtils.subtract(oldList, newList);
        Collection<String> addList = CollectionUtils.subtract(newList, oldList);
        Collection<String> updateList = CollectionUtils.intersection(oldList, newList);
        Map<String, Object> map = new HashedMap();
        map.put(ADDLIST, addList);
        map.put(DELETELIST, deleteList);
        map.put(UPDATELIST, updateList);
        return map;
    }


}