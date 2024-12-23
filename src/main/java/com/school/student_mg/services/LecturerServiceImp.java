package com.school.student_mg.services;

import com.school.student_mg.exception.NotFoundException;
import com.school.student_mg.models.Lecturer;
import com.school.student_mg.repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerServiceImp implements LecturerService{

    @Autowired
    private LectureRepository lectureRepository;

    //Sửa lại
    @Override
    public Lecturer addLecturer(Lecturer lecturer) {
        return lectureRepository.save(lecturer);
    }

    @Override
    public Lecturer getLecturerById(String id) {
        return lectureRepository.findById(id).orElseThrow(()->new NotFoundException(false, "Lecturer not found"));
    }

    @Override
    public Lecturer updateLecturer(Lecturer lecturer, String id) {
        return null;
    }

    @Override
    public List<Lecturer> getAllLecturer() {
        return List.of();
    }

    @Override
    public void deleteLecturer(String id) {

    }
}
