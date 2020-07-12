package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "curative_effect")
public class CurativeEffectEntity {
    private int    id;
    private String evaluateResult;
    private String evaluateResultDetail;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurativeEffectEntity that = (CurativeEffectEntity) o;
        return id == that.id &&
                Objects.equals(evaluateResult, that.evaluateResult) &&
                Objects.equals(evaluateResultDetail, that.evaluateResultDetail) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, evaluateResult, evaluateResultDetail, status);
    }
}
