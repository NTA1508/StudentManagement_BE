package com.school.student_mg.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Faculty {
    @Id
    private String fId;
    @PrePersist
    private void generateId(){
        if(fId == null || fId.isEmpty()){
            fId = UUID.randomUUID().toString();
        }
    }

    private String facultyName;
    private String facultyRoom;
    private String email;
    private Date foundingDate;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "deanId", referencedColumnName = "lId", unique = true)
    private Lecturer dean;

    public Faculty(String fId, String facultyName, String facultyRoom, String email, Date foundingDate, String phoneNumber, Lecturer dean) {
        this.fId = fId;
        this.facultyName = facultyName;
        this.facultyRoom = facultyRoom;
        this.email = email;
        this.foundingDate = foundingDate;
        this.phoneNumber = phoneNumber;
        this.dean = dean;
    }

    public Faculty() {
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyRoom() {
        return facultyRoom;
    }

    public void setFacultyRoom(String facultyRoom) {
        this.facultyRoom = facultyRoom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Lecturer getDean() {
        return dean;
    }

    public void setDean(Lecturer dean) {
        this.dean = dean;
    }
}
