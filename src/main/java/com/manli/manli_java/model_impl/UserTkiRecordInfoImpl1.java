package com.manli.manli_java.model_impl;

import java.sql.Date;

public class UserTkiRecordInfoImpl1 {
    private int    userId;
    private String tkiName;
    private Date   tkiStartDate;
    private Short  tkiDosage;
    private Byte   tkiFrequency;
    private byte   tkiOrder;
    public UserTkiRecordInfoImpl1() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTkiName() {
        return tkiName;
    }

    public void setTkiName(String tkiName) {
        this.tkiName = tkiName;
    }

    public Date getTkiStartDate() {
        return tkiStartDate;
    }

    public void setTkiStartDate(Date tkiStartDate) {
        this.tkiStartDate = tkiStartDate;
    }

    public Short getTkiDosage() {
        return tkiDosage;
    }

    public void setTkiDosage(Short tkiDosage) {
        this.tkiDosage = tkiDosage;
    }

    public Byte getTkiFrequency() {
        return tkiFrequency;
    }

    public void setTkiFrequency(Byte tkiFrequency) {
        this.tkiFrequency = tkiFrequency;
    }

    public byte getTkiOrder() {
        return tkiOrder;
    }

    public void setTkiOrder(byte tkiOrder) {
        this.tkiOrder = tkiOrder;
    }

}
