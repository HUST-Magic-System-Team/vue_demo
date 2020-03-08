package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user_record_info")
public class UserRecordInfoEntity {
    private int     id;
    private int     userId;
    private String  hospital;
    private String  doctor;
    private String  profession;
    private Integer weight;
    private String  compl;
    private Integer bChaoDown;
    private Integer bChaoLength;
    private Integer bChaoDepth;
    private Integer bloodTestWhite;
    private Integer bloodTestRed;
    private Integer bloodTestPlatelet;
    private String  marrowCells;
    private String  gene;
    private String  chromosome;
    private Date    confirmDate;
    private Date    firstMedicineDate;
    private String  cml;
    private Byte    combinedChemotherapy;
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
    @Column(name = "hospital")
    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    @Basic
    @Column(name = "doctor")
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Basic
    @Column(name = "profession")
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Basic
    @Column(name = "weight")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "compl")
    public String getCompl() {
        return compl;
    }

    public void setCompl(String compl) {
        this.compl = compl;
    }

    @Basic
    @Column(name = "bChaoDown")
    public Integer getbChaoDown() {
        return bChaoDown;
    }

    public void setbChaoDown(Integer bChaoDown) {
        this.bChaoDown = bChaoDown;
    }

    @Basic
    @Column(name = "bChaoLength")
    public Integer getbChaoLength() {
        return bChaoLength;
    }

    public void setbChaoLength(Integer bChaoLength) {
        this.bChaoLength = bChaoLength;
    }

    @Basic
    @Column(name = "bChaoDepth")
    public Integer getbChaoDepth() {
        return bChaoDepth;
    }

    public void setbChaoDepth(Integer bChaoDepth) {
        this.bChaoDepth = bChaoDepth;
    }

    @Basic
    @Column(name = "bloodTestWhite")
    public Integer getBloodTestWhite() {
        return bloodTestWhite;
    }

    public void setBloodTestWhite(Integer bloodTestWhite) {
        this.bloodTestWhite = bloodTestWhite;
    }

    @Basic
    @Column(name = "bloodTestRed")
    public Integer getBloodTestRed() {
        return bloodTestRed;
    }

    public void setBloodTestRed(Integer bloodTestRed) {
        this.bloodTestRed = bloodTestRed;
    }

    @Basic
    @Column(name = "bloodTestPlatelet")
    public Integer getBloodTestPlatelet() {
        return bloodTestPlatelet;
    }

    public void setBloodTestPlatelet(Integer bloodTestPlatelet) {
        this.bloodTestPlatelet = bloodTestPlatelet;
    }

    @Basic
    @Column(name = "marrowCells")
    public String getMarrowCells() {
        return marrowCells;
    }

    public void setMarrowCells(String marrowCells) {
        this.marrowCells = marrowCells;
    }

    @Basic
    @Column(name = "gene")
    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    @Basic
    @Column(name = "chromosome")
    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    @Basic
    @Column(name = "confirmDate")
    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    @Basic
    @Column(name = "firstMedicineDate")
    public Date getFirstMedicineDate() {
        return firstMedicineDate;
    }

    public void setFirstMedicineDate(Date firstMedicineDate) {
        this.firstMedicineDate = firstMedicineDate;
    }

    @Basic
    @Column(name = "cml")
    public String getCml() {
        return cml;
    }

    public void setCml(String cml) {
        this.cml = cml;
    }

    @Basic
    @Column(name = "combinedChemotherapy")
    public Byte getCombinedChemotherapy() {
        return combinedChemotherapy;
    }

    public void setCombinedChemotherapy(Byte combinedChemotherapy) {
        this.combinedChemotherapy = combinedChemotherapy;
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
        UserRecordInfoEntity that = (UserRecordInfoEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(hospital, that.hospital) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(profession, that.profession) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(compl, that.compl) &&
                Objects.equals(bChaoDown, that.bChaoDown) &&
                Objects.equals(bChaoLength, that.bChaoLength) &&
                Objects.equals(bChaoDepth, that.bChaoDepth) &&
                Objects.equals(bloodTestWhite, that.bloodTestWhite) &&
                Objects.equals(bloodTestRed, that.bloodTestRed) &&
                Objects.equals(bloodTestPlatelet, that.bloodTestPlatelet) &&
                Objects.equals(marrowCells, that.marrowCells) &&
                Objects.equals(gene, that.gene) &&
                Objects.equals(chromosome, that.chromosome) &&
                Objects.equals(confirmDate, that.confirmDate) &&
                Objects.equals(firstMedicineDate, that.firstMedicineDate) &&
                Objects.equals(cml, that.cml) &&
                Objects.equals(combinedChemotherapy, that.combinedChemotherapy) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, hospital, doctor, profession, weight, compl, bChaoDown, bChaoLength, bChaoDepth, bloodTestWhite, bloodTestRed, bloodTestPlatelet, marrowCells, gene, chromosome, confirmDate, firstMedicineDate, cml, combinedChemotherapy, status);
    }

}
