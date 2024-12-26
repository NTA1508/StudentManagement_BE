package com.school.student_mg.controllers;

import com.school.student_mg.dto.FacultyDto;
import com.school.student_mg.models.Faculty;
import com.school.student_mg.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping("/add")
    public ResponseEntity<?> addFaculty(@RequestBody FacultyDto facultyDto){
        Faculty faculty = new Faculty();
        faculty.setFacultyName(facultyDto.getFacultyName());
        faculty.setFacultyRoom(facultyDto.getFacultyRoom());
        faculty.setEmail(facultyDto.getEmail());
        faculty.setFoundingDate(facultyDto.getFoundingDate());
        faculty.setPhoneNumber(facultyDto.getPhoneNumber());

        Faculty createFaculty = facultyService.addFaculty(faculty, facultyDto.getLecturerId());
        return ResponseEntity.ok(createFaculty);
    }

    @GetMapping("/faculties")
    public ResponseEntity<?> getAllFaculty(){
        return new ResponseEntity<List<FacultyDto>>(facultyService.getAllFaculty(), HttpStatus.OK);
    }
}
