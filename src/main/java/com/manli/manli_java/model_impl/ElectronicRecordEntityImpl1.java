package com.manli.manli_java.model_impl;

import com.manli.manli_java.model_auto.ElectronicRecordEntity;
import com.manli.manli_java.model_auto.MedicinePlanEntity;

public class ElectronicRecordEntityImpl1 extends ElectronicRecordEntity {
     String group;
     String url;
    public ElectronicRecordEntityImpl1(ElectronicRecordEntity orig) {
        this.setId(orig.getId());
        this.setUserId(orig.getUserId());
        this.setHashName(orig.getHashName());
        this.setCheckTime(orig.getCheckTime());
        this.setUploadTime(orig.getUploadTime());
        this.setGroupId(orig.getGroupId());
        this.setStatus(orig.getStatus());
        this.setGroup("病历组"+orig.getGroupId());
        this.setUrl("http://down.cml922.com/electronic_record/"+this.getHashName());
    }
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
