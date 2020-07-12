package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "phone_smscode")
public class PhoneSmscodeEntity {
    private int       id;
    private String    phone;
    private String    smscode;
    private Timestamp expire;

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
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "smscode")
    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }

    @Basic
    @Column(name = "expire")
    public Timestamp getExpire() {
        return expire;
    }

    public void setExpire(Timestamp expire) {
        this.expire = expire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneSmscodeEntity that = (PhoneSmscodeEntity) o;
        return id == that.id &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(smscode, that.smscode) &&
                Objects.equals(expire, that.expire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, smscode, expire);
    }
}
