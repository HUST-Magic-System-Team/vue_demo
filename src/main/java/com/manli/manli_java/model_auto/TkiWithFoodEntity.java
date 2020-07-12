package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tki_with_food")
public class TkiWithFoodEntity {
    private int    id;
    private String food;
    private String suggestion;
    private String reason;
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
    @Column(name = "food")
    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
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
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        TkiWithFoodEntity that = (TkiWithFoodEntity) o;
        return id == that.id &&
                Objects.equals(food, that.food) &&
                Objects.equals(suggestion, that.suggestion) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, food, suggestion, reason, status);
    }
}
