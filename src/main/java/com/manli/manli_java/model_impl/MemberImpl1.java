package com.manli.manli_java.model_impl;

public class MemberImpl1 {

    /**
     * userId : 7
     * name : zhaoxx
     * phone : 13800001111
     * email : zhaxx@qq.com
     * location : xxx
     * qq : 12345678
     * address : xxx
     * isMember : true
     */

    private int userId;
    private String name;
    private String phone;
    private String email;
    private String location;
    private String qq;
    private String address;
    private boolean isMember;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIsMember() {
        return isMember;
    }

    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }
}
