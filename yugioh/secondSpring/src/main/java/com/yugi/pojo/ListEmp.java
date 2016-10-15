package com.yugi.pojo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/1/29.
 */
public class ListEmp {

    private List<Emp> emp;

    private Emp[] emps;

    @Override
    public String toString() {
        return "ListEmp{" +
                "emp=" + emp +
                ", emps=" + Arrays.toString(emps) +
                '}';
    }

    public Emp[] getEmps() {
        return emps;
    }

    public void setEmps(Emp[] emps) {
        this.emps = emps;
    }

    public List<Emp> getEmp() {
        return emp;
    }

    public void setEmp(List<Emp> emp) {
        this.emp = emp;
    }
}
