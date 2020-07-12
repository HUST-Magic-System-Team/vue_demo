package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tki_with_medicine_noresult")
public class TkiWithMedicineNoresultEntity {
    private int       id;
    private int       userId;
    private String    tki;
    private String    tkiLabel;
    private String    medicine;
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
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "tki")
    public String getTki() {
        return tki;
    }

    public void setTki(String tki) {
        this.tki = tki;
    }

    @Basic
    @Column(name = "tkiLabel")
    public String getTkiLabel() {
        return tkiLabel;
    }

    public void setTkiLabel(String tkiLabel) {
        this.tkiLabel = tkiLabel;
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
        TkiWithMedicineNoresultEntity that = (TkiWithMedicineNoresultEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(tki, that.tki) &&
                Objects.equals(tkiLabel, that.tkiLabel) &&
                Objects.equals(medicine, that.medicine) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, tki, tkiLabel, medicine, status, createdAt);
    }
}
