package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "member")
public class MemberEntity {
    private int    id;
    private int    userId;
    private String name;
    private String phone;
    private String email;
    private String location;
    private String qq;
    private String address;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        MemberEntity that = (MemberEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(location, that.location) &&
                Objects.equals(qq, that.qq) &&
                Objects.equals(address, that.address) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, phone, email, location, qq, address, status);
    }
}
