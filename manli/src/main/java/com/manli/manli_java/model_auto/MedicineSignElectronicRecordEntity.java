package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "medicine_sign_electronic_record")
public class MedicineSignElectronicRecordEntity {
    private int    id;
    private int    medicineSignId;
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
    @Column(name = "medicineSignId")
    public int getMedicineSignId() {
        return medicineSignId;
    }

    public void setMedicineSignId(int medicineSignId) {
        this.medicineSignId = medicineSignId;
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
        MedicineSignElectronicRecordEntity that = (MedicineSignElectronicRecordEntity) o;
        return id == that.id &&
                medicineSignId == that.medicineSignId &&
                Objects.equals(hashName, that.hashName) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicineSignId, hashName, status);
    }
}
