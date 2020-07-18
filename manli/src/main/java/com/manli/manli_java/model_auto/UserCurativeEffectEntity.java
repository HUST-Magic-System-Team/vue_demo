package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_curative_effect")
public class UserCurativeEffectEntity {
    private int       id;
    private int       bcrAblIs;
    private Date      bcrAblTime;
    private int       userId;
    private Date      startTime;
    private Integer   month;
    private String    evaluateResult;
    private String    evaluateResultDetail;
    private Byte      status;
    private Timestamp createdAt;

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
    @Column(name = "bcrAblIs")
    public int getBcrAblIs() {
        return bcrAblIs;
    }

    public void setBcrAblIs(int bcrAblIs) {
        this.bcrAblIs = bcrAblIs;
    }

    @Basic
    @Column(name = "bcrAblTime")
    public Date getBcrAblTime() {
        return bcrAblTime;
    }

    public void setBcrAblTime(Date bcrAblTime) {
        this.bcrAblTime = bcrAblTime;
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
    @Column(name = "startTime")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "month")
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Basic
    @Column(name = "evaluateResult")
    public String getEvaluateResult() {
        return evaluateResult;
    }

    public void setEvaluateResult(String evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Basic
    @Column(name = "evaluateResultDetail")
    public String getEvaluateResultDetail() {
        return evaluateResultDetail;
    }

    public void setEvaluateResultDetail(String evaluateResultDetail) {
        this.evaluateResultDetail = evaluateResultDetail;
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
    @Column(name = "createdAt")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCurativeEffectEntity that = (UserCurativeEffectEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(bcrAblIs, that.bcrAblIs) &&
                Objects.equals(bcrAblTime, that.bcrAblTime) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(month, that.month) &&
                Objects.equals(evaluateResult, that.evaluateResult) &&
                Objects.equals(evaluateResultDetail, that.evaluateResultDetail) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bcrAblIs, bcrAblTime, userId, startTime, month, evaluateResult, evaluateResultDetail, status, createdAt);
    }
}
