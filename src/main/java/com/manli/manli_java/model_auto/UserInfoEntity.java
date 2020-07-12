package com.manli.manli_java.model_auto;


import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_info")
public class UserInfoEntity {
    private int     id;
    private int     userId;
    private int     takingMedicineTime;
    private String  interestTags;
    private String  medals;
    private Integer manliCoin;
    private String  phone;
    private Byte    sex;
    private String  name;
    private Date    birthDay;
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
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "takingMedicineTime")
    public int getTakingMedicineTime() {
        return takingMedicineTime;
    }

    public void setTakingMedicineTime(int takingMedicineTime) {
        this.takingMedicineTime = takingMedicineTime;
    }

    @Basic
    @Column(name = "interestTags")
    public String getInterestTags() {
        return interestTags;
    }

    public void setInterestTags(String interestTags) {
        this.interestTags = interestTags;
    }

    @Basic
    @Column(name = "medals")
    public String getMedals() {
        return medals;
    }

    public void setMedals(String medals) {
        this.medals = medals;
    }

    @Basic
    @Column(name = "manliCoin")
    public Integer getManliCoin() {
        return manliCoin;
    }

    public void setManliCoin(Integer manliCoin) {
        this.manliCoin = manliCoin;
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
    @Column(name = "sex")
    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
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
    @Column(name = "birthDay")
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
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
        UserInfoEntity that = (UserInfoEntity) o;
        return id == that.id &&
                userId == that.userId &&
                takingMedicineTime == that.takingMedicineTime &&
                Objects.equals(interestTags, that.interestTags) &&
                Objects.equals(medals, that.medals) &&
                Objects.equals(manliCoin, that.manliCoin) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthDay, that.birthDay) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, takingMedicineTime, interestTags, medals, manliCoin, phone, sex, name, birthDay, status);
    }


}
