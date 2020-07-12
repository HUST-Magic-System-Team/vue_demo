package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ios_set")
public class IosSetEntity {
    private int    id;
    private String url;
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
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        IosSetEntity that = (IosSetEntity) o;
        return id == that.id &&
                Objects.equals(url, that.url) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, status);
    }
}
