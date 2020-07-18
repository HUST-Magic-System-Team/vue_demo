package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bad_effect_treatment")
public class BadEffectTreatmentEntity {
    private short  id;
    private Byte   badEffectLevel;
    private String treatment;
    private Byte   status;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "badEffectLevel")
    public Byte getBadEffectLevel() {
        return badEffectLevel;
    }

    public void setBadEffectLevel(Byte badEffectLevel) {
        this.badEffectLevel = badEffectLevel;
    }

    @Basic
    @Column(name = "treatment")
    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
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
        BadEffectTreatmentEntity that = (BadEffectTreatmentEntity) o;
        return id == that.id &&
                Objects.equals(badEffectLevel, that.badEffectLevel) &&
                Objects.equals(treatment, that.treatment) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, badEffectLevel, treatment, status);
    }
}
