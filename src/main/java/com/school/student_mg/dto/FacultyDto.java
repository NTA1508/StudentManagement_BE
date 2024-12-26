package com.school.student_mg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.school.student_mg.models.Faculty;
import com.school.student_mg.models.Lecturer;

import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacultyDto {
    private String fId;
    private String facultyName;
    private String facultyRoom;
    private String email;
    private Date foundingDate;
    private String phoneNumber;
    private String lecturerId;
    private Lecturer dean;

    public FacultyDto() {
    }

    public FacultyDto(String fId, String facultyName, String facultyRoom, String email, Date foundingDate, String phoneNumber, String lecturerId) {
        this.fId = fId;
        this.facultyName = facultyName;
        this.facultyRoom = facultyRoom;
        this.email = email;
        this.foundingDate = foundingDate;
        this.phoneNumber = phoneNumber;
        this.lecturerId = lecturerId;
    }

    public FacultyDto(Faculty faculty){
        this.fId = faculty.getfId();
        this.facultyName = faculty.getFacultyName();
        this.facultyRoom = faculty.getFacultyRoom();
        this.email = faculty.getEmail();
        this.foundingDate = faculty.getFoundingDate();
        this.phoneNumber = faculty.getPhoneNumber();
        this.dean = faculty.getDean();
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

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Lecturer getDean() {
        return dean;
    }

    public void setDean(Lecturer dean) {
        this.dean = dean;
    }
}
