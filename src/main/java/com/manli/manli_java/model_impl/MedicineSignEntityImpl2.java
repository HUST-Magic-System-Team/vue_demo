package com.manli.manli_java.model_impl;

import java.sql.Date;

public class MedicineSignEntityImpl2 {

    public java.sql.Date signDate;
    public Integer       signCount;
    public String        notify;
    public MedicineSignEntityImpl2() {
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

}
