package com.school.student_mg.services;

import com.school.student_mg.models.AuthenticationResponse;
import com.school.student_mg.models.Lecturer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LecturerService {
    public AuthenticationResponse addLecturer(Lecturer lecturer);
    public Lecturer getLecturerById(String id);
    public Lecturer updateLecturer(Lecturer lecturer, String id);
    public List<Lecturer> getAllLecturer();
    public void deleteLecturer(String id);
}
