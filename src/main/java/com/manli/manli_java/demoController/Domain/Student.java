package com.manli.manli_java.demoController.Domain;

import java.io.Serializable;

public class Student implements Serializable {
    private String  sex;
    private int     age;
    private boolean goodStu;
    public Student(String sex, int age, boolean goodStu) {
        this.sex = sex;
        this.age = age;
        this.goodStu = goodStu;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGoodStu() {
        return goodStu;
    }

    public void setGoodStu(boolean goodStu) {
        this.goodStu = goodStu;
    }


}
