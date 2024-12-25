package com.school.student_mg.dto;

import com.school.student_mg.models.Gender;
import com.school.student_mg.models.Lecturer;
import com.school.student_mg.models.Role;

import java.util.Date;
import java.util.Set;

public class LecturerDto {
    private String lId;
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date dob;
    private String phoneNumber;
    private String address;
    private String status;
    private Set<Role> roles;

    public LecturerDto(Lecturer lecturer) {
        this.lId = lecturer.getlId();
        this.email = lecturer.getEmail();
        this.firstName = lecturer.getFirstName();
        this.lastName = lecturer.getLastName();
        this.gender = lecturer.getGender();
        this.dob = lecturer.getDob();
        this.phoneNumber = lecturer.getPhoneNumber();
        this.address = lecturer.getAddress();
        this.status = lecturer.getStatus();
        this.roles = lecturer.getRoles();
    }

    public String getlId() {
        return lId;
    }

    public void setlId(String lId) {
        this.lId = lId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
