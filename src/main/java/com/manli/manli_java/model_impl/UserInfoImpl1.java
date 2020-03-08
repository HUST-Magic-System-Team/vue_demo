package com.manli.manli_java.model_impl;

import java.sql.Date;

public class UserInfoImpl1 {
    public String phone;
    public Byte   sex;
    public String name;
    public Date   birthDay;

    public UserInfoImpl1() {

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
