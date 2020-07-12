package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bad_effect")
public class BadEffectEntity {
    private int     id;
    private String  badEffect;
    private Byte    badEffectLevel;
    private String  expression;
    private Integer position;
    private Byte    status;

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
    @Column(name = "badEffect")
    public String getBadEffect() {
        return badEffect;
    }

    public void setBadEffect(String badEffect) {
        this.badEffect = badEffect;
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
    @Column(name = "expression")
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Basic
    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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
        BadEffectEntity that = (BadEffectEntity) o;
        return id == that.id &&
                Objects.equals(badEffect, that.badEffect) &&
                Objects.equals(badEffectLevel, that.badEffectLevel) &&
                Objects.equals(expression, that.expression) &&
                Objects.equals(position, that.position) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, badEffect, badEffectLevel, expression, position, status);
    }
}
