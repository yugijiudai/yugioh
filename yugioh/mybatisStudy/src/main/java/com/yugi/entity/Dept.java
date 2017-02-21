package com.yugi.entity;

/**
 * Created by Administrator on 2017/1/26.
 */
public class Dept {

    private Integer deptno;
    private Integer empId;
    private String name;

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", empId=" + empId +
                ", name='" + name + '\'' +
                '}';
    }
}
