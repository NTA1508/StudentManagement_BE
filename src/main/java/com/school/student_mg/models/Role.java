package com.school.student_mg.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Role {

    public Role(int id, String roleName, Set<Lecturer> lecturers) {
        this.id = id;
        this.roleName = roleName;
        this.lecturers = lecturers;
    }

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<Lecturer> lecturers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }
}
