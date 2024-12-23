package com.school.student_mg.repositories;

import com.school.student_mg.models.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecturer, String> {
}
