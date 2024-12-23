package com.school.student_mg.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.util.UUID;

@Entity
public class Parent {

    public Parent(String pId, String fatherName, String motherName, String fatherPhoneNumber, String motherPhoneNumber, String fatherJob, String motherJob, String parentAddress) {
        this.pId = pId;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.fatherPhoneNumber = fatherPhoneNumber;
        this.motherPhoneNumber = motherPhoneNumber;
        this.fatherJob = fatherJob;
        this.motherJob = motherJob;
        this.parentAddress = parentAddress;
    }

    public Parent() {
    }

    @Id
    private String pId;
    @PrePersist
    private void generateId() {
        if (pId == null || pId.isEmpty()) {
            pId = UUID.randomUUID().toString();
        }
    }


    private String fatherName;
    private String motherName;
    @Column(name = "fatherPhoneNumber", length = 15)
    private String fatherPhoneNumber;
    @Column(name = "motherPhoneNumber", length = 15)
    private String motherPhoneNumber;
    private String fatherJob;
    private String motherJob;
    private String parentAddress;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherPhoneNumber() {
        return fatherPhoneNumber;
    }

    public void setFatherPhoneNumber(String fatherPhoneNumber) {
        this.fatherPhoneNumber = fatherPhoneNumber;
    }

    public String getMotherPhoneNumber() {
        return motherPhoneNumber;
    }

    public void setMotherPhoneNumber(String motherPhoneNumber) {
        this.motherPhoneNumber = motherPhoneNumber;
    }

    public String getFatherJob() {
        return fatherJob;
    }

    public void setFatherJob(String fatherJob) {
        this.fatherJob = fatherJob;
    }

    public String getMotherJob() {
        return motherJob;
    }

    public void setMotherJob(String motherJob) {
        this.motherJob = motherJob;
    }

    public String getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(String parentAddress) {
        this.parentAddress = parentAddress;
    }
}
