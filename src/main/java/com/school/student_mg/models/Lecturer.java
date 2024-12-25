package com.school.student_mg.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class Lecturer {

    public Lecturer(String lId, String email, String password, String firstName, String lastName, Gender gender, Date dob, String phoneNumber, String address, String status, Set<Role> roles) {
        this.lId = lId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.roles = roles;
    }

    public Lecturer() {
    }

    @Id
    private String lId;
    @PrePersist
    private void generatedId(){
        if(lId == null || lId.isEmpty()){
            lId = UUID.randomUUID().toString();
        }
    }

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private Date dob;

    @Column(name = "phoneNumber", length = 15)
    private String phoneNumber;
    private String address;
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "lecturer_role",
            joinColumns = @JoinColumn(name = "lecturer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
