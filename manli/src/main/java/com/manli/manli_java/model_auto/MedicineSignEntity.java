package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "medicine_sign")
public class MedicineSignEntity {
    private int    id;
    private int    userId;
    private int    medicinePlanId;
    private Date   signDate;
    private String signTime;
    private Short  eatMedicineOnTime;
    private int    dosage;
    private String badEffect;
    private String badEffectDetail;
    private Byte   status;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "medicinePlanId")
    public int getMedicinePlanId() {
        return medicinePlanId;
    }

    public void setMedicinePlanId(int medicinePlanId) {
        this.medicinePlanId = medicinePlanId;
    }

    @Basic
    @Column(name = "signDate")
    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    @Basic
    @Column(name = "signTime")
    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    @Basic
    @Column(name = "eatMedicineOnTime")
    public Short getEatMedicineOnTime() {
        return eatMedicineOnTime;
    }

    public void setEatMedicineOnTime(Short eatMedicineOnTime) {
        this.eatMedicineOnTime = eatMedicineOnTime;
    }

    @Basic
    @Column(name = "dosage")
    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    @Basic
    @Column(name = "badEffect")
    public String getBadEffect() {
        return badEffect;
    }

    public void setBadEffect(String badEffect) {
        this.badEffect = badEffect;
    }

    @Basic
    @Column(name = "badEffectDetail")
    public String getBadEffectDetail() {
        return badEffectDetail;
    }

    public void setBadEffectDetail(String badEffectDetail) {
        this.badEffectDetail = badEffectDetail;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineSignEntity that = (MedicineSignEntity) o;
        return id == that.id &&
                userId == that.userId &&
                medicinePlanId == that.medicinePlanId &&
                dosage == that.dosage &&
                Objects.equals(signDate, that.signDate) &&
                Objects.equals(signTime, that.signTime) &&
                Objects.equals(eatMedicineOnTime, that.eatMedicineOnTime) &&
                Objects.equals(badEffect, that.badEffect) &&
                Objects.equals(badEffectDetail, that.badEffectDetail) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, medicinePlanId, signDate, signTime, eatMedicineOnTime, dosage, badEffect, badEffectDetail, status);
    }
}
