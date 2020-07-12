package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "admin_user_token")
public class AdminUserTokenEntity {
    private int       id;
    private String    adminUserName;
    private String    adminToken;
    private Timestamp updatedAt;
    private Timestamp createdAt;

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
    @Column(name = "adminUserName")
    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    @Basic
    @Column(name = "adminToken")
    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminUserTokenEntity that = (AdminUserTokenEntity) o;
        return id == that.id &&
                Objects.equals(adminUserName, that.adminUserName) &&
                Objects.equals(adminToken, that.adminToken) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adminUserName, adminToken, updatedAt, createdAt);
    }
}
