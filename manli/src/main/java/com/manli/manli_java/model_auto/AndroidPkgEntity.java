package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "android_pkg")
public class AndroidPkgEntity {
    private int    id;
    private String version;
    private String hashName;
    private Byte   isDefaultPkg;
    private String desc;
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
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "hashName")
    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
    }

    @Basic
    @Column(name = "isDefaultPkg")
    public Byte getIsDefaultPkg() {
        return isDefaultPkg;
    }

    public void setIsDefaultPkg(Byte isDefaultPkg) {
        this.isDefaultPkg = isDefaultPkg;
    }

    @Basic
    @Column(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        AndroidPkgEntity that = (AndroidPkgEntity) o;
        return id == that.id &&
                Objects.equals(version, that.version) &&
                Objects.equals(hashName, that.hashName) &&
                Objects.equals(isDefaultPkg, that.isDefaultPkg) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, hashName, isDefaultPkg, desc, status);
    }
}
