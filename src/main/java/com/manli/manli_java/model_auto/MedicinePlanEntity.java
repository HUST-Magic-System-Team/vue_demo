package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "medicine_plan")
public class MedicinePlanEntity {
    private int    id;
    private int    userId;
    private String medicine;
    private int    dosage;
    private int    frequency;
    private String notify;
    private String eatTimeByDay;
    private Date   startTime;
    private int    duration;
    private Byte   status;
    private Short  isMain;

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
    @Column(name = "medicine")
    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
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
    @Column(name = "frequency")
    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Basic
    @Column(name = "notify")
    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    @Basic
    @Column(name = "eatTimeByDay")
    public String getEatTimeByDay() {
        return eatTimeByDay;
    }

    public void setEatTimeByDay(String eatTimeByDay) {
        this.eatTimeByDay = eatTimeByDay;
    }

    @Basic
    @Column(name = "startTime")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "isMain")
    public Short getIsMain() {
        return isMain;
    }

    public void setIsMain(Short isMain) {
        this.isMain = isMain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicinePlanEntity that = (MedicinePlanEntity) o;
        return id == that.id &&
                userId == that.userId &&
                dosage == that.dosage &&
                frequency == that.frequency &&
                duration == that.duration &&
                Objects.equals(medicine, that.medicine) &&
                Objects.equals(notify, that.notify) &&
                Objects.equals(eatTimeByDay, that.eatTimeByDay) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(status, that.status) &&
                Objects.equals(isMain, that.isMain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, medicine, dosage, frequency, notify, eatTimeByDay, startTime, duration, status, isMain);
    }
}
