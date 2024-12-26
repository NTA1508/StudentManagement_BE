package com.school.student_mg.services;

import com.school.student_mg.dto.FacultyDto;
import com.school.student_mg.models.Faculty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacultyService {
    public Faculty addFaculty(Faculty faculty, String lecturerId);
    public Faculty updateFaculty(Faculty faculty, String id);
    public Faculty getFacultyById(String id);
    public List<FacultyDto> getAllFaculty();
    public void deleteFaculty(String id);
}
