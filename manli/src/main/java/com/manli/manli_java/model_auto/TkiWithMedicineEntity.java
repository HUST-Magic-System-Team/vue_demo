package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tki_with_medicine")
public class TkiWithMedicineEntity {
    private int    id;
    private String tki;
    private String tkiLabel;
    private String medicine;
    private String suggestion;
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
    @Column(name = "suggestion")
    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
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
        TkiWithMedicineEntity that = (TkiWithMedicineEntity) o;
        return id == that.id &&
                Objects.equals(tki, that.tki) &&
                Objects.equals(tkiLabel, that.tkiLabel) &&
                Objects.equals(medicine, that.medicine) &&
                Objects.equals(suggestion, that.suggestion) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tki, tkiLabel, medicine, suggestion, status);
    }
}
