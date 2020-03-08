package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user_tki_record_info")
public class UserTkiRecordInfoEntity {
    private int    id;
    private int    userId;
    private String tkiName;
    private Date   tkiStartDate;
    private Short  tkiDosage;
    private Byte   tkiFrequency;
    private byte   tkiOrder;
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
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "tkiName")
    public String getTkiName() {
        return tkiName;
    }

    public void setTkiName(String tkiName) {
        this.tkiName = tkiName;
    }

    @Basic
    @Column(name = "tkiStartDate")
    public Date getTkiStartDate() {
        return tkiStartDate;
    }

    public void setTkiStartDate(Date tkiStartDate) {
        this.tkiStartDate = tkiStartDate;
    }

    @Basic
    @Column(name = "tkiDosage")
    public Short getTkiDosage() {
        return tkiDosage;
    }

    public void setTkiDosage(Short tkiDosage) {
        this.tkiDosage = tkiDosage;
    }

    @Basic
    @Column(name = "tkiFrequency")
    public Byte getTkiFrequency() {
        return tkiFrequency;
    }

    public void setTkiFrequency(Byte tkiFrequency) {
        this.tkiFrequency = tkiFrequency;
    }

    @Basic
    @Column(name = "tkiOrder")
    public byte getTkiOrder() {
        return tkiOrder;
    }

    public void setTkiOrder(byte tkiOrder) {
        this.tkiOrder = tkiOrder;
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
        UserTkiRecordInfoEntity that = (UserTkiRecordInfoEntity) o;
        return id == that.id &&
                userId == that.userId &&
                tkiOrder == that.tkiOrder &&
                Objects.equals(tkiName, that.tkiName) &&
                Objects.equals(tkiStartDate, that.tkiStartDate) &&
                Objects.equals(tkiDosage, that.tkiDosage) &&
                Objects.equals(tkiFrequency, that.tkiFrequency) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, tkiName, tkiStartDate, tkiDosage, tkiFrequency, tkiOrder, status);
    }

}
