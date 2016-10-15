package com.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public final class Observe {

    private static final List<Notifier> lists = createList();


    private Observe() {
    }

    private static List<Notifier> createList() {
        List<Notifier> list = new ArrayList<>();
        list.add(new Invest());
        list.add(new BuyLoan());
        return list;
    }

    public static void notifyPlayers(){
        for (Notifier list : lists) {
            list.doHanlde();
        }
    }


}
