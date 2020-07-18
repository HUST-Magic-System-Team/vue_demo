package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_curative_effect_electronic_record")
public class UserCurativeEffectElectronicRecordEntity {
    private int    id;
    private int    userCurativeEffectId;
    private String hashName;
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
    @Column(name = "userCurativeEffectId")
    public int getUserCurativeEffectId() {
        return userCurativeEffectId;
    }

    public void setUserCurativeEffectId(int userCurativeEffectId) {
        this.userCurativeEffectId = userCurativeEffectId;
    }

    @Basic
    @Column(name = "hashName")
    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
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
        UserCurativeEffectElectronicRecordEntity that = (UserCurativeEffectElectronicRecordEntity) o;
        return id == that.id &&
                userCurativeEffectId == that.userCurativeEffectId &&
                Objects.equals(hashName, that.hashName) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userCurativeEffectId, hashName, status);
    }
}
