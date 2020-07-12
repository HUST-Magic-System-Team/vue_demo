package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_medal")
public class UserMedalEntity {
    private int       id;
    private int       userId;
    private Byte      type;
    private Timestamp awardTime;
    private String    title;
    private String    detail;
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
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "type")
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "awardTime")
    public Timestamp getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Timestamp awardTime) {
        this.awardTime = awardTime;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
        UserMedalEntity that = (UserMedalEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(type, that.type) &&
                Objects.equals(awardTime, that.awardTime) &&
                Objects.equals(title, that.title) &&
                Objects.equals(detail, that.detail) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, type, awardTime, title, detail, status);
    }
}
