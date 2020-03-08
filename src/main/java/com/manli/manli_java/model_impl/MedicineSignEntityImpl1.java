package com.manli.manli_java.model_impl;

import com.manli.manli_java.model_auto.MedicineSignEntity;

public class MedicineSignEntityImpl1 extends MedicineSignEntity {
    String  medicine;
    Integer frequency;
    Short   isMain;
    public MedicineSignEntityImpl1() {
        super();
    }
    public MedicineSignEntityImpl1(MedicineSignEntity orig) {
        this.setId(orig.getId());
        this.setId(orig.getUserId());
        this.setMedicinePlanId(orig.getMedicinePlanId());
        this.setSignDate(orig.getSignDate());
        this.setSignTime(orig.getSignTime());
        this.setEatMedicineOnTime(orig.getEatMedicineOnTime());
        this.setDosage(orig.getDosage());
        this.setBadEffect(orig.getBadEffect());
        this.setBadEffectDetail(orig.getBadEffectDetail());
        this.setStatus(orig.getStatus());
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Short getIsMain() {
        return isMain;
    }

    public void setIsMain(Short isMain) {
        this.isMain = isMain;
    }


}
