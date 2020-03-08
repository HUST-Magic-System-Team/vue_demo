package com.manli.manli_java.model_impl;

import com.manli.manli_java.model_auto.UserBadEffectEntity;

public class UserBadEffectEntityImpl1 extends UserBadEffectEntity {
    String treatment;

    public UserBadEffectEntityImpl1(UserBadEffectEntity orig) {
        this.setId(orig.getId());
        this.setUserId(orig.getUserId());
        this.setBadEffect(orig.getBadEffect());
        this.setBadEffectDetail(orig.getBadEffectDetail());
        this.setBadEffectLevel(orig.getBadEffectLevel());
        this.setCreatedAt(orig.getCreatedAt());
        this.setStatus(orig.getStatus());
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

}
