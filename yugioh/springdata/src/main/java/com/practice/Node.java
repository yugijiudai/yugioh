package com.practice;

import java.util.Stack;

/**
 * Created by Administrator on 2016/4/5.
 */
public class Node {

    public Node left;
    public Node right;
    public String value;
    boolean find;
    public Stack<String> stack = new Stack<>();

    public Stack<String> find(Node node, String val) {
        if (node != null && !find) {
            stack.push(node.value);
            if (node.value.equals(val)) {
                find = true;
                return stack;
            }
            else {
                find(node.left, val);
                find(node.right, val);
                if (!find) {
                    stack.pop();
                }
            }
        }
        return stack;
    }

}
