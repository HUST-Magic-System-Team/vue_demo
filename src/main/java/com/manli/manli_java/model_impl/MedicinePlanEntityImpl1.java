package com.manli.manli_java.model_impl;

import com.manli.manli_java.model_auto.MedicinePlanEntity;
import com.manli.manli_java.model_auto.MedicineSignEntity;

import java.util.List;

//接口26用
public class MedicinePlanEntityImpl1 extends MedicinePlanEntity {
    public List<MedicineSignEntity> medicineSignEntityArray;

    public MedicinePlanEntityImpl1() {
        super();
    }

    public MedicinePlanEntityImpl1(MedicinePlanEntity orig) {
        this.setId(orig.getId());
        this.setUserId(orig.getUserId());
        this.setMedicine(orig.getMedicine());
        this.setDosage(orig.getDosage());
        this.setFrequency(orig.getFrequency());
        this.setNotify(orig.getNotify());
        this.setEatTimeByDay(orig.getEatTimeByDay());
        this.setStartTime(orig.getStartTime());
        this.setDuration(orig.getDuration());
        this.setStatus(orig.getStatus());
        this.setIsMain(orig.getIsMain());
    }

    public List<MedicineSignEntity> getMedicineSignEntityArray() {
        return medicineSignEntityArray;
    }

    public void setMedicineSignEntityArray(List<MedicineSignEntity> medicineSignEntityArray) {
        this.medicineSignEntityArray = medicineSignEntityArray;
    }
}
