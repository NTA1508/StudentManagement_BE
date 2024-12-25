package com.school.student_mg.repositories;

import com.school.student_mg.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r FROM Role r JOIN r.lecturers l WHERE l.email = :email")
    List<Role> findRolesByLecturerEmail(@Param("email") String email);
}
