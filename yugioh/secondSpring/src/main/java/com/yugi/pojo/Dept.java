package com.yugi.pojo;

/**
 * Created by Administrator on 2016/1/29.
 */
public class Dept {

    private Integer deptno;

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                '}';
    }
}
