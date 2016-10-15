package com;

import com.practice.Node;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by Administrator on 2016/4/5.
 */

public class TestNode {


    @Test
    public void testFind() {
        Node a = new Node();
        Node b = new Node();
        Node c = new Node();
        Node d = new Node();
        Node e = new Node();
        a.left = b;
        a.right = c;
        b.left = d;
        a.value = "A";
        b.value = "B";
        c.value = "C";
        d.value = "D";
        e.value = "E";
        b.right = e;
        Stack<String> stack = a.find(a,"E");
        for (String s : stack) {
            System.out.print(s + "->");
        }
    }
}
