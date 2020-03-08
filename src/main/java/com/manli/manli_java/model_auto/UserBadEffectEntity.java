package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_bad_effect")
public class UserBadEffectEntity {
    private int       id;
    private Integer   userId;
    private String    badEffect;
    private String    badEffectDetail;
    private Integer   badEffectLevel;
    private Timestamp createdAt;
    private Byte      status;

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
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
    @Column(name = "badEffectLevel")
    public Integer getBadEffectLevel() {
        return badEffectLevel;
    }

    public void setBadEffectLevel(Integer badEffectLevel) {
        this.badEffectLevel = badEffectLevel;
    }

    @Basic
    @Column(name = "createdAt")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
        UserBadEffectEntity that = (UserBadEffectEntity) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(badEffect, that.badEffect) &&
                Objects.equals(badEffectDetail, that.badEffectDetail) &&
                Objects.equals(badEffectLevel, that.badEffectLevel) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, badEffect, badEffectDetail, badEffectLevel, createdAt, status);
    }
}
